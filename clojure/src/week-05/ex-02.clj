; a)
(def length 4)
(def height 2)


; fläche
(defn area [a b] (* a b))
(area length height)

; umfang
(defn circumference [a b] (* 2 (+ a b)))
(circumference length height)

; diagonale
(defn diagonal [a b] (Math/sqrt (+ (* a a) (* b b))))
(diagonal length height)


; b)
(def width 8)

; volumen
(defn volume [a b c] (* a b c))
(volume length width height)

; oberfläche
(defn surface [a b c] (* 2 (+ (* a b) (* a c) (* b c))))
(surface length width height)