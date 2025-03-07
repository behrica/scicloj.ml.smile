(defproject scicloj/scicloj.ml.smile "5.07-SNAPSHOT"
  :description "Smile models for scicloj.ml"
  :url "https://github.com/scicloj/scicloj.ml.smile"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.2"]
                 [scicloj/tablecloth "5.17"]
                 [scicloj/metamorph.ml "0.3.0-beta3"]
                 [org.bytedeco/openblas "0.3.10-1.5.4"]
                 [org.bytedeco/openblas-platform "0.3.10-1.5.4"]
                 [generateme/fastmath "2.1.0"]
                 ]


  :profiles
  {:test
   {:dependencies []}

   :codox
   {:dependencies [[codox-theme-rdash "0.1.2"]]
    :plugins [[lein-codox "0.10.7"]]
    :codox {:project {:name "tech.ml"}
            :metadata {:doc/format :markdown}
            :namespaces [
                         scicloj.ml.smile.classification
                         scicloj.ml.smile.regression]
            :themes [:rdash]
            :source-paths ["src"]
            :output-path "docs"
            :doc-paths ["topics"]
            :source-uri "https://github.com/techascent/tech.ml/blob/master/{filepath}#L{line}"}}}
  :aliases {"codox" ["with-profile" "codox,dev" "codox"]}
  :java-source-paths ["java"])
