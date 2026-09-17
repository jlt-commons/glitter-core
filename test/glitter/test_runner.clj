(ns glitter.test-runner
  "Entry point for `jolt -M:test`. Requires each glitter-core test
  namespace and runs clojure.test against it. Prints a summary; exits
  non-zero if anything failed (so the :test task fails CI). Mirrors
  glitter's own test/glitter/test_runner.clj, trimmed to the namespaces
  that live in this package."
  (:require [clojure.test :as t]))

(defmethod t/report :error [m]
  (t/with-test-out
    (t/inc-report-counter :error)
    (println "\nERROR in" (t/testing-vars-str m))
    (when (seq t/*testing-contexts*) (println (t/testing-contexts-str)))
    (when-let [message (:message m)] (println message))
    (when-let [e (:actual m)]
      (if (instance? Throwable e)
        (do (println "  ->" (.getName (class e)) ":" (ex-message e))
            (when-let [d (ex-data e)] (prn d))
            (when-let [c (ex-cause e)]
              (println "  caused by:" (.getName (class c)) ":" (ex-message c))))
        (prn e)))))

(defn- exit
  "Terminate the process with `code`. Call System/exit DIRECTLY — see
  glitter's own test-runner docstring for why a resolve-based guard
  never fires under Jolt."
  [code]
  (System/exit code))

(defn -main [& _]
  (let [namespaces '[glitter.hiccup-test glitter.assert-test glitter.asserts-test
                     glitter.core-test glitter.alias-test
                     glitter.nexus.action-log-test]
        broken (atom [])]
    (doseq [ns namespaces]
      (try (require ns :reload)
           (catch Exception e
             (swap! broken conj ns)
             (println "ERROR requiring" ns ":" (ex-message e)))))
    (let [loaded  (remove (set @broken) namespaces)
          results (if (seq loaded)
                    (apply t/run-tests loaded)
                    {:test 0 :pass 0 :fail 0 :error 0})
          failed  (+ (:fail results 0) (:error results 0) (count @broken))]
      (println "----")
      (when (seq @broken)
        (println "FAILED TO LOAD:" (count @broken) "of" (count namespaces)
                 "namespaces:" (pr-str @broken))
        (println "  a namespace that will not load is a failure, not an absence"))
      (println "tests:" (:test results 0)
               "assertions:" (:pass results 0) "passed /"
               failed "failed")
      (when (pos? failed) (exit 1)))))
