# Getting started

**What you'll have at the end of this page:** Jolt installed, this
project's test suite running green, and a working directory ready to
depend on `glitter-core` from another Jolt project.

## Install Jolt

`glitter-core` runs on [Jolt](https://github.com/jolt-lang/jolt), native
Clojure on Chez Scheme — no JVM anywhere. Follow Jolt's own install
instructions, then confirm:

```bash
jolt --version
```

This project declares `:jolt/min-version "0.7.24"` in `deps.edn` — an
older Jolt refuses to load it rather than running with subtly wrong
behavior.

## Clone and test

```bash
git clone https://github.com/jlt-commons/glitter-core
cd glitter-core
jolt -M:test
```

A clean run reports every test passing, zero failures, zero errors.

## Depend on it from another project

Add a pinned git coordinate to your own `deps.edn`:

```clojure
io.github.jlt-commons/glitter-core
{:git/url "https://github.com/jlt-commons/glitter-core"
 :git/sha "<the commit you want>"}
```

Then require `glitter.core`, `glitter.protocols`, or any of the other
`glitter.*` namespaces this package ships — the names are unchanged from
`glitter` itself, since the extraction was designed to be a
non-breaking dependency change for existing consumers.

Run `bb tasks` (or `jolt tasks` for a Jolt-language project) to see
every available command, including the site-build tasks below.
