(ns frinra.core
  (:require
            [frinra.concentration :as conc]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
(defn filter-doses [values]
  (map (fn [pair] (pair 1)) values))

(defn -main
  []
  (println (filter-doses (conc/concentrations conc/doses conc/dates)))
                        )