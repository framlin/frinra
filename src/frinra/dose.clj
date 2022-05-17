(ns frinra.dose)

(def NUMBER-OF-DAYS 7)
(def NUMBER-PER-TABLET 4)

(defn fr-split
  "fr-split (1 2 3) -> ((1) (2) (3))
   fr-split (1 2 3 4 5 6 7) -> ((1 2 3) (4) (5 6 7))"
  [coll]
  (let [length (count coll) sub-size (quot length 2)]
    (list
      (doall (take sub-size coll))
      (list (nth coll sub-size))
      (take-last sub-size coll))))

(defn fill-quarters-for-a-week [week-dose]
  (let [quarters-per-day (int (quot (* week-dose NUMBER-PER-TABLET) NUMBER-OF-DAYS))]
    (repeat NUMBER-OF-DAYS quarters-per-day)))


(defn dispatch-one-day
  [already-dispatched inverse]
  (if inverse already-dispatched (map #(+ 1 %) already-dispatched)))


(defn dispatch-outstanding
  [already-dispatched outstanding inverse]
  (let [l (count already-dispatched)
        n (if inverse (- l outstanding) outstanding)
        left-part (first (fr-split already-dispatched))
        middle (second (fr-split already-dispatched))
        right-part (last (fr-split already-dispatched))]
    (cond
      (= outstanding 0) already-dispatched
      (= l 1) (dispatch-one-day already-dispatched inverse)
      (= n 1) (if inverse
                (list (map inc left-part) middle (map inc right-part))
                (list left-part (inc (first middle)) right-part))
      :else (list
              (dispatch-outstanding left-part (int (quot outstanding 2)) inverse)
              (dispatch-outstanding middle (int (mod outstanding 2)) inverse)
              (dispatch-outstanding right-part (int (quot outstanding 2)) inverse)))))


(defn dispatch [week-dose]
  (let [week-dose-as-quarters (* week-dose NUMBER-PER-TABLET)
        quarters-for-a-week (fill-quarters-for-a-week week-dose)
        outstanding (int (mod week-dose-as-quarters NUMBER-OF-DAYS))
        inverse (> outstanding 3)]
    (if (= outstanding 0) (map (fn [quarters] (/ quarters NUMBER-PER-TABLET)) quarters-for-a-week)
                          (map (fn [quarters] (/ quarters NUMBER-PER-TABLET))
                               (flatten (dispatch-outstanding quarters-for-a-week outstanding inverse))))))

