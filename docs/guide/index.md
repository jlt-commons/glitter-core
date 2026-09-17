# glitter-core

The natives-free two-thirds of [glitter](https://github.com/jlt-commons/glitter):
its Replicant-style reconciler (`glitter.core`), the `IRender`/`IMemory`
protocol seam (`glitter.protocols`), hiccup parsing, vdom, assertions,
error handling, a headless in-memory test renderer, and glitter's own
nexus action-log accumulator. No `:jolt/native` declaration anywhere in
this package's `deps.edn` — a consumer never needs GTK4, GLib, or any
other native library installed to depend on it.

`glitter.core` talks to the live tree only through `IRender`/`IMemory`.
It has no idea what's on the other side of those protocols: GTK4 (in
`glitter` itself), AppKit (in
[`glitter-uikit`](https://github.com/jlt-commons/glitter-uikit)), an
in-memory fake (this repo's own `glitter.test-renderer`), or, eventually,
UIKit.

## How to read this guide

Start with Getting Started to run the test suite, then Architecture for
how the pieces fit together and why this package exists as its own repo.
Contributing covers the porting conventions if you're touching ported
code rather than writing new code.

## Guide map

| Page | What you'll learn |
|---|---|
| [Getting started](getting-started.md) | Install Jolt, run the test suite |
| [Architecture](architecture.md) | The `IRender`/`IMemory` seam, and why this split from `glitter` |
| [Contributing](contributing.md) | Porting conventions, provenance rules, how to run tests |

## Find your scenario

| Scenario | Pages to read |
|---|---|
| "I want to try this out" | Getting started |
| "I want to understand how it works" | Architecture |
| "I want to contribute a change" | Contributing |
