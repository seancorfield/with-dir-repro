# Repro

Clone the repo and then run:

```bash
clojure -T:build prep
clojure -T:build uber
```

The script prints the `:paths` and `:classpath` roots from the default basis:
no `:paths` and the classpath has Clojure 1.10.3 on it (as expected).

Then the script prints the `:paths` and `:classpath` roots from the basis call
wrapped in `with-dir`:
`src` is present in both, as expected from the `projects/example/deps.edn` file.

But neither the `prep` task not the `uber` task respect the `with-dir`
context so the top-level `src` folder is used instead of the `projects/example/src` folder.
