(defn print-number [n]
  (when (> n 1)
    (println n)
    (if (zero? (rem n 2))
      (print-number (/ n 2))
      (print-number (+ (* 3 n) 1)))))

(print-number 3)
(print-number 7)
  