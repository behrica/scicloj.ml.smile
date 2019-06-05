(defproject techascent/tech.ml "1.0-alpha6"
  :description "Base concepts of the techascent ml suite"
  :url "http://github.com/techascent/tech.ml-base"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[techascent/tech.ml.dataset "1.0-alpha10"]
                 [techascent/tech.io "2.11"]
                 [ml.dmlc/xgboost4j "0.81"]]

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.1-beta2"]
                                  [ch.qos.logback/logback-classic "1.1.3"]
                                  [metasoarous/oz "1.5.2"]]}}
  :aot [tech.v2.datatype]

  :java-source-paths ["java"])
