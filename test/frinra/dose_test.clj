(ns frinra.dose_test
  (:require [clojure.test :refer :all]
            [frinra.dose :refer :all]))


(deftest test-dispatch
  (testing "dispatch")
  (and
    (is (= (dispatch 0.0) (list 0 0 0 0 0 0 0)))
    (is (= (dispatch 0.25) (list 0 0 0 1/4 0 0 0)))
    (is (= (dispatch 0.5) (list 0 1/4 0 0 0 1/4 0)))
    (is (= (dispatch 0.75) (list 0 1/4 0 1/4 0 1/4 0)))
    (is (= (dispatch 1) (list 1/4 0 1/4 0 1/4 0 1/4)))

    (is (= (dispatch 7) (list 1 1 1 1 1 1 1)))
    (is (= (dispatch 7.25) (list 1 1 1 5/4 1 1 1)))
    (is (= (dispatch 7.5) (list 1 5/4 1 1 1 5/4 1)))

    (is (= (dispatch 4.5) (list 3/4 1/2 3/4 1/2 3/4 1/2 3/4)))
    (is (= (dispatch 4.25) (list 2/4 3/4 2/4 3/4 2/4 3/4 2/4)))
    (is (= (dispatch 3.75) (list 2/4 2/4 2/4 3/4 2/4 2/4 2/4)))

    (is (= (dispatch 8) (list 5/4 1 5/4 1 5/4 1 5/4)))
    ))


(deftest test-dispatch-whole-tablets-for-a-week
  (testing "Testing, dispatching whole tablets of a Week-Dose")
  (and
    (is (= (fill-quarters-for-a-week 7) (list 4 4 4 4 4 4 4)))
    (is (= (fill-quarters-for-a-week 14) (list 8 8 8 8 8 8 8)))
    (is (= (fill-quarters-for-a-week 4.25) (list 2 2 2 2 2 2 2)))))


(deftest test-fr-split
  (testing "Testing, if a sequence will be split correct in (s1 m s1)")
  (and
    (is (= (fr-split '(1 2 3 4 5 6 7))
           '((1 2 3) (4) (5 6 7))))
    (is (= (fr-split '(1 2 3))
           '((1) (2) (3))))))