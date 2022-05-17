(ns frinra.concentration-test
  (:require [clojure.test :refer :all]
            [frinra.concentration :refer :all]))

(deftest test-concentration
  (testing "concentration of a particular day")
  (is (= (concentration '(1 1 1 1 1 1)) 3.5)))

(deftest test-concentration-short
  (testing "concentration with to less values")
  (or
    (is (= (concentration '(1 1 1 1)) 0))
    (is (= (concentration '(1)) 0)) ))