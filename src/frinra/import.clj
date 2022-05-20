(ns frinra.import
  (:require [frinra.io :as frio]))

(defn comma->dot
  [num-string]
  (clojure.string/replace num-string #"," "."))

(defn filter-inr-data
  [data-line]
  (let [date 0, inr 1, finger 3]
    [(data-line date), (comma->dot (data-line inr)), (data-line finger)]))

(defn filter-dose
  [data-line]
  (let [date 0, dose 2]
    [(data-line date), (comma->dot (data-line dose))] ))

(defn import-data [in-fname out-fname filter]
  (let [csv-data (frio/load-csv in-fname)]
    (frio/write-csv out-fname (map filter (rest csv-data)))))

