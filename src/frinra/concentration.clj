(ns frinra.concentration
  (:require [frinra.io :as frio] )
 )

(defn parse-date [date-str]
  date-str)

(defn filter-doses [dose-csv]
  (map (fn [line] (Float/parseFloat (line 1))) dose-csv)
)

(defn filter-dates [dose-csv]
  (map (fn [line] (parse-date (line 0))) dose-csv)
  )

(defn tail-padding [data]
  (flatten (cons data '(0 0 0 0 0))))

(defn front-padding [data]
  (flatten (cons '(0 0 0 0 0) data )))

(def doses (tail-padding (filter-doses (frio/load-csv frio/fn-doses))))
(def dates (tail-padding (filter-dates (frio/load-csv frio/fn-doses))))
(def dose-csv (frio/load-csv frio/fn-doses))

(defn concentration [doses dates]
  (if (< (count doses) 6) 0
  (let [one-to-five (take 5 (iterate inc 1))
        first-five (take 5 doses)
        last-val (/ (nth doses 5) 2)
        date (nth dates 5)
        sum-over-five (reduce + (map *  first-five one-to-five))]
    [date (float (+ (/ sum-over-five 5) last-val)) ] )))


(defn concentrations [doses dates]
  (let [                                                    ;doses (doall (tail-padding (filter-doses dose-csv-data)))
        tail-doses (rest doses)
        tail-dates (rest dates)
        concentr (concentration doses dates)]
    (if (= (count doses) 6)
      (list concentr)
      (cons concentr (concentrations tail-doses tail-dates)) )))

(defn save [concentrs]
  (frio/write-csv frio/fn-concentration concentrs) )