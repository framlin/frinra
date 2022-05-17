(ns frinra.io)
(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io])

(def fn-doses "resources/dose.csv")
(def fn-inr "resources/INR.csv")
(def fn-export "resources/exported-INR.csv")
(def fn-concentration "resources/concentration.csv")


(defn load-csv [fname] (with-open [reader (io/reader fname)]
                         (doall
                           (csv/read-csv reader))))


(defn write-csv [fname csv-data]
  (with-open [writer (io/writer fname)]
    (csv/write-csv writer csv-data)))

