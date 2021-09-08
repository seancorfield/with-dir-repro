(ns build
  (:require [clojure.java.io :as io]
            [clojure.tools.build.api :as b]
            [clojure.tools.deps.alpha.util.dir :refer [with-dir]]))

(def lib 'my/lib1)
(def version (format "1.2.%s" (b/git-count-revs nil)))
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))

(def project-dir (io/file (str (System/getProperty "user.dir") "/projects/example")))

(defn clean [_]
  (b/delete {:path "target"}))

(defn prep [_]
  (with-dir project-dir
    (b/write-pom {:class-dir class-dir
                  :lib lib
                  :version version
                  :basis basis
                  :src-dirs ["src"]})
    (b/copy-dir {:src-dirs ["src" "resources"]
                 :target-dir class-dir})))

(defn uber [_]
  (with-dir project-dir
    (b/compile-clj {:basis basis
                    :src-dirs ["src"]
                    :class-dir class-dir})
    (b/uber {:class-dir class-dir
             :uber-file uber-file
             :basis basis})))