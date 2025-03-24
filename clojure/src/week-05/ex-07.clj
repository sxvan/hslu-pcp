; a)
(defn chf_to_eur [v] (* v 1.05))
(chf_to_eur 5)

; b)
(defn celsius_to_fahrenheit [v] (+ (* v 1.8) 32))
(celsius_to_fahrenheit 4)

; c)
(defn scale [x1 x2 y1 y2 v] 
  (+ y1 (* (/ (- y2 y1) (- x2 x1)) (- v x1))))

(scale 0.0 100.0 32.0 212.0 30.0)

(scale 1 2 1.05 2.10 10)
