(defn diskriminante [a b c] (- (* b b) (* 4 a c)))

; a)
(defn mitternachtsformel? [a b c]
  (not (neg? (diskriminante a b c))))

(mitternachtsformel? 1 -3 1)
; b)
(defn mitternachtsformel_count [a b c] 
  (let [diskriminante (diskriminante a b c)]
    (cond
      (neg? diskriminante) 0
      (= diskriminante 0) 1
      :else 2)))


(mitternachtsformel_count 1 -3 1)

; c)
(defn mitternachtsformel [a b c] 
  (let [diskriminante (diskriminante a b c)]
    (cond
      (neg? diskriminante) []
      (= diskriminante 0) [(/ (+ (- b) (Math/sqrt diskriminante) (* 2 a)))]
      :else 
      [(/ (+ (- b) (Math/sqrt diskriminante) (* 2 a)))
       (/ (- (- b) (Math/sqrt diskriminante) (* 2 a)))])))

(mitternachtsformel 1 -3 1)
  