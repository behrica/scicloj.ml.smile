(ns tech.v3.libs.smile.sparse-svm
  (:require
   [tech.v3.datatype :as dt]
   [tech.v3.datatype.errors :as errors]
   [tech.v3.dataset :as ds]
   [tech.v3.dataset.modelling :as ds-mod]
   [tech.v3.libs.smile.discrete-nb :as nb]
   [tech.v3.libs.smile.nlp :as nlp]
   [scicloj.metamorph.ml :as ml])
  (:import [smile.classification SVM]
           [smile.data SparseDataset]
           [smile.util SparseArray]))




(defn train [feature-ds target-ds options]
  "Training function of sparse SVM model.
   The column of name `(options :sparse-column)` of `feature-ds` needs to contain the text as SparseArrays
   over the vocabulary."
  (let [train-array (into-array SparseArray (get feature-ds (options :sparse-column)))
        score (get target-ds (first (ds-mod/inference-target-column-names target-ds)))
        p (:p options)
        _ (errors/when-not-error (pos? p) "p needs to be specified in options and greater 0")
        ]
    (SVM/fit train-array
             (dt/->int-array score)
             p
             ^double (get options :C 1.0)
             ^double (get options :tol 1e-4))))


(defn predict [feature-ds
                      thawed-model
                      model]
  "Predict function for sparse SVM model"
  (let [sparse-arrays (into-array ^SparseArray  (get feature-ds (get-in model [:options :sparse-column])))
        target-colum (first (:target-columns model))
        predictions (.predict (:model-data model) sparse-arrays) ]
    (ds/->dataset {target-colum predictions})) )

(ml/define-model!
  :smile.classification/sparse-svm
  train
  predict
  {})

