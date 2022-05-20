(ns frinra.io
(:require [clojure.data.csv :as csv]
         [clojure.java.io :as io]))

(def fn-doses (if (.exists (io/file "../../resources/dose.csv")) "../../resources/dose.csv" "resources/dose.csv"))
(def fn-inr (if (.exists (io/file "../../resources/INR.csv")) "../../resources/INR.csv" "resources/INR.csv"))
(def fn-export (if (.exists (io/file "../../resources/exported-INR.csv")) "../../resources/exported-INR.csv" "resources/exported-INR.csv"))
(def fn-concentration (if (.exists (io/file "../../resources/concentration.csv")) "../../resources/concentration.csv" "resources/concentration.csv"))


(defn load-csv [fname] (with-open [reader (io/reader fname)]
                         (doall
                           (csv/read-csv reader))))


(defn write-csv [fname csv-data]
  (with-open [writer (io/writer fname)]
    (csv/write-csv writer csv-data)))

