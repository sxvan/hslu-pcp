; a)
(defn sum-range [a b] 
  (if (> a b)
    0
    (+ a (sum-range (inc a) b))))

(sum-range 1 5)
(sum-range 3 9)

; b)
(defn sum-squares [a b] 
  (if (> a b)
    0
    (+ (* a a) (sum-squares (inc a) b))))

(sum-squares 1 4)