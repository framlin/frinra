(ns frinra.gui
  (:require [quil.core :as q] [frinra.concentration :as conc]))

(def WIN-HEIGHT 600)
(def WIN-WIDTH 800)
(def MARGIN-LEFT 20)
(def MARGIN-RIGHT 20)
(def MARGIN-BOTTOM 20)
(def MARGIN-TOP 20)

(defn setup []
  (q/frame-rate 1)
  (q/background 200))

(defn draw-value-bar [index value]
  (let [bar-bottom (- WIN-HEIGHT MARGIN-BOTTOM)
        bar-x (+ MARGIN-LEFT (* index 2))
        bar-top (- bar-bottom value)]
    (q/line bar-x bar-bottom bar-x bar-top))
  )

(defn draw-value-point [index value]
  (let [val (int (* 100 value))
        bottom-line (- WIN-HEIGHT MARGIN-BOTTOM)
        point-x (+ MARGIN-LEFT (* index 10))
        point-y (- bottom-line val)]
    (q/ellipse point-x point-y 4 4))
  )

(defn draw-concentrations [concentrations-list]
  (doall (map-indexed draw-value-point concentrations-list ))
  )
(defn filter-doses [values]
  (map (fn [pair] (pair 1)) values))

(def conc-list (filter-doses (conc/concentrations conc/doses conc/dates)))

(defn draw []
  (q/line MARGIN-LEFT MARGIN-TOP MARGIN-LEFT (- WIN-HEIGHT MARGIN-BOTTOM) )
  (q/line MARGIN-LEFT (- WIN-HEIGHT MARGIN-BOTTOM) (- WIN-WIDTH MARGIN-RIGHT) (- WIN-HEIGHT MARGIN-BOTTOM))
  (q/fill 220 200 255)
  (draw-concentrations conc-list)
  (q/fill 220 0 0)
  (draw-concentrations conc/doses)
  )

;(conc/concentrations conc/doses conc/dates)

(q/defsketch inr-values
             :title "INR values"    ;; Set the title of the sketch
             :settings #(q/smooth 2)             ;; Turn on anti-aliasing
             :setup setup                        ;; Specify the setup fn
             :draw draw                          ;; Specify the draw fn
             :size [WIN-WIDTH WIN-HEIGHT])                    ;; You struggle to beat the golden ratio
