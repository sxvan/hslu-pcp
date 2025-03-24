; a)
; Nein, weil es nur prüft bis die erste bedingung true ist. Wenn man die Bedingungen tauscht sind aber mehrere Bedingungen true
(def temperatur 30)

(cond
(> temperatur 35) "heiss"
(> temperatur 25) "warm"
(> temperatur 15) "mittel"
:else "kalt")

; b)
; Ja, es verändert das verhalten des programms aber es ist nicht falsch.
(def zahl 6)

(cond
(zero? (rem zahl 2)) "durch 2 teilbar"
(zero? (rem zahl 3)) "durch 3 teilbar"
:else "weder durch 2 noch durch 3 teilbar")

; c)
(if (> temperatur 35)
  "heiss"
  (if (> temperatur 25)
    "warm"
    (if (> temperatur 15)
      "mittel"
      "kalt")))

; d)
; Nein, weil case nur für exakte werte funktioniert und nicht für bedingungen wie bei a). 
; Case unterstützt auch nicht funktionen wie zero? oder rem die bei b) verwendet werden.