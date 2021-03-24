(ns tech.v3.ml.metamorph-test
  (:require [scicloj.metamorph.ml :as ml]
            [clojure.test :refer [deftest is]]
            [tech.v3.dataset.column-filters :as cf]
            [tech.v3.dataset.modelling :as ds-mod]
            [tech.v3.dataset :as ds]
            [scicloj.metamorph.ml.loss :as loss]
            [scicloj.metamorph.ml.classification]
            [tech.v3.libs.smile.classification]
            ))


(deftest test-model
  (let [
        src-ds (ds/->dataset "test/data/iris.csv")
        ds (->  src-ds
                (ds/categorical->number cf/categorical)
                (ds-mod/set-inference-target "species")
                (ds/shuffle {:seed 1234}))
        feature-ds (cf/feature ds)
        split-data (ds-mod/train-test-split ds {:randomize-dataset? false})
        train-ds (:train-ds split-data)
        test-ds  (:test-ds split-data)

        pipeline (fn  [ctx]
                   ((ml/model {:model-type :smile.classification/random-forest})
                    ctx))


        fitted
        (pipeline
         {:metamorph/id "1"
          :metamorph/mode :fit
          :metamorph/data train-ds})


        prediction
        (pipeline (merge fitted
                         {:metamorph/mode :transform
                          :metamorph/data test-ds}))

        predicted-species (ds-mod/column-values->categorical (:metamorph/data prediction)
                                                            "species"
                                                            )]

    (is (= ["setosa" "setosa" "virginica"]
           (take 3 predicted-species)))

    ))
