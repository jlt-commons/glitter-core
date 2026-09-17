# glitter-core

The natives-free two-thirds of [glitter](https://github.com/jlt-commons/glitter):
its Replicant-style reconciler (`glitter.core`), the `IRender`/`IMemory`
protocol seam (`glitter.protocols`), hiccup parsing, vdom, assertions,
error handling, a headless in-memory test renderer, and glitter's own
action-log accumulator. No `:jolt/native` declaration anywhere in this
package's `deps.edn` — a consumer never needs GTK4, GLib, or any other
native library installed to depend on it.

`glitter.core` talks to the live tree only through `IRender`/`IMemory`.
It has no idea what's on the other side of those protocols — GTK4 (in
`glitter` itself), AppKit (in `glitter-uikit`), an in-memory fake (in
this repo's own `glitter.test-renderer`, ships in `src/` so consuming
apps can reuse it in their own test suites), or, eventually, UIKit.

Extracted from `glitter` because Jolt inherits a dependency's declared
natives transitively and hard-fails before any namespace loads if one is
missing — even for a consumer, like `glitter-uikit`, that never calls a
GTK function. See `glitter`'s own README/CHANGELOG for the extraction
rationale in full, and this project's own design spec.

## Quick start

```bash
jolt -M:test
```

## Architecture

Every file here requires only another file in this same package (or
`tick.core`, from `jolt-lang/time`, for `glitter.nexus.action-log`'s
timestamps) — nothing GTK-, GLib-, or FFI-shaped. `glitter` itself now
depends on this package for exactly these namespaces, and supplies
`glitter.gtk`/`glitter.widget`/`glitter.ffi`/`glitter.genum`/
`glitter.app` on top.

## Status

Early — a direct extraction with no scope beyond what already existed in
`glitter`. CHANGELOG and a docs guide are deferred until there's a
reason to diverge from glitter's own documentation of the same code.

## Licence

Copyright (c) 2026 Burin Choomnuan. Distributed under the
[Eclipse Public License 2.0](LICENSE). The ported Replicant and nexus
code keeps its own MIT license — see [`NOTICE`](NOTICE).
