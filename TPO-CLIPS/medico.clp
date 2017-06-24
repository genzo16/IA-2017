
(deftemplate Tratamiento (slot idPaciente (type STRING))
                                (multislot tiempo (type STRING) (default "Ninguno" ) (allowed-strings  "Ninguno" "de 4 a 5 dias" "7 dias" "0 dias"))
                                (multislot estado (type STRING) (default "Ninguno" )  (allowed-strings  "Ninguno" "Terminado" "En tratamiento" ))
                                (multislot recomendaciones (type STRING) (default "Ninguno" ) (allowed-strings  "Ninguno" "Reposo" "Reposo absoluto" "No realizar actividad fisica" "Realizar movimientos suaves sin fuerza" "Realizar gargaras con agua tibia con sal (repetir varias veces al dia)" "Realizar estudio aspiracion de abceso"))
                                (multislot visitas (type STRING) (default "Ninguno" )  (allowed-strings  "Ninguno" "Volver en 6 dias" "Volver en 8 dias" "Finalizado"))
                                (multislot observaciones (type STRING)  (default "Ninguno" ) (allowed-strings  "Ninguno" "No se observa mejoria"  "Realizar estudios medicos adicionales" "Paciente aliviado" "Paciente no aliviado"))
                                (multislot derivaciones (type STRING) (default "Ninguno" )  (allowed-strings  "Ninguno" "Abceso periamigdalino" "Enfermedad renal" )))




(deftemplate Sintomas-Paciente (slot idPaciente (type STRING))
                               (multislot descripcionMalestar (type STRING) (default "Ninguno") (allowed-strings "Ninguno" "Garganta"))
                               (multislot sintomas (type STRING) (default "Ninguno") (allowed-strings "Ninguno" "Fiebre" "Dolor de cabeza" "Dolor de garganta"))
                               (multislot acotaciones (type STRING) (default "Ninguno")))




(deftemplate Historial-Medico (slot idPaciente (type STRING))
                                     (slot fecha-tratamiento (type STRING)(default "Ninguno"))
                                     (multislot tipoTratamiento (type STRING) (default "Ninguno") (allowed-strings "Ninguno" "Terminado" "Medicacion analgesico" "Ingesta suave (liquidos y solidos)" "Reposo" "Medicacion analgesico-antibiotico" "Ingesta sumamente suave (liquidos y solidos)" "Reposo absoluto" "No realizar actividades")))
 




(deftemplate Paciente (multislot nombre (type STRING))
                                         (multislot apellido (type STRING))
                                         (slot dni (type STRING)))




 



(deftemplate Garganta (slot idPaciente (type STRING))
                             (multislot estado (type STRING) (default  "Ninguno" )  (allowed-strings "No Irritada"  "Irritada"  "Ninguno" ))
                             (multislot amigdalas (type STRING) (default   "Ninguno" )  (allowed-strings  "Normal" "Inflamada" "Ninguno"))
                             (multislot estudioMedico (type STRING) (default  "Ninguno" )  (allowed-strings "Ninguno" "Prueba Estreptococica"))
                             (multislot estudioAdicional (type STRING) (default  "Ninguno" )  (allowed-strings  "Ninguno" "Cultivo Faringeo"))
                             (multislot observaciones (type STRING)  (default  "Ninguno" ))
                             (multislot infeccion (type STRING) (default "Ninguno") (allowed-strings "Presencia de infeccion" "Sin infeccion" "Ninguno"))
                             (multislot examinacionAdicional (type STRING) (default  "Ninguno" ) (allowed-strings  "Ninguno" "Oido" "Nariz")))


(deftemplate Amigdalas (slot idPaciente (type STRING))
                       (multislot tamanio (type STRING)(default "Ninguno")(allowed-strings "Normal" "Grandes" "Ninguno"))
                       (multislot color (type STRING)(default "Ninguno")(allowed-strings "Normal" "Rojiza" "Ninguno"))
                       (multislot placas-blancas (type STRING)(default "Ninguno") (allowed-strings "Ninguno" "Presentes" "No hay")))




(deftemplate Estreptococo (slot idPaciente (type STRING))
                        (slot estado (type STRING) (default "Ninguno" )  (allowed-strings "Ninguno" "Negativo" "Positivo")))




(deftemplate Cultivo-Faringeo (slot idPaciente (type STRING))
                                     (slot zona (type STRING) (default "Ninguno") (allowed-strings "Ninguno" "Garganta" "Oido" "Nariz"))
                                     (slot resultado (type STRING) (default "Ninguno") (allowed-strings   "Ninguno"  "Negativo" "Positivo"))
                                     (multislot  colonizacionBacteriana (type STRING) (default "Ninguno" )  (allowed-strings  "Ninguno"  "Estreptococo" )))






(deftemplate Oido (slot idPaciente (type STRING))
                         (multislot estado (type STRING)  (default  "Ninguno" )  (allowed-strings  "Ninguno"  "Sin infeccion" "Infeccion")))




(deftemplate Nariz (slot idPaciente (type STRING))
                          (multislot estado (type STRING) (default "Ninguno" )  (allowed-strings  "Sin infeccion" "Infeccion" "Ninguno")))







(deftemplate Diagnostico (slot idPaciente (type STRING))
                                (multislot enfermedad (type STRING) (default  "Ninguno") (allowed-strings "Ninguno" "Amigdalitis"))
                                (multislot zonaTratar (type STRING) (default  "Ninguno" )  (allowed-strings "Ninguno"  "Garganta")))
 
	





(deftemplate Receta (slot idPaciente (type STRING))
                           (multislot antibiotico (type STRING) (default "Ninguno" ) (allowed-strings "Ninguno" "Amoxilina"))
                           (multislot  analgesico (type STRING) (default "Ninguno" ) (allowed-strings "Ninguno"  "Ibuprofeno"))
                           (multislot liquidos (type STRING) (default "Ninguno" )  (allowed-strings "Ninguno"  "Zumo citrico" "Te con limon" "Bebidas calientes" "Abundante agua"))
                           (multislot solidos (type STRING) (default "Ninguno" )  (allowed-strings "Ninguno" "Pure" "Sopa Crema" "Harina de avena" "Ensalada de frutas suaves" "Comidas no dificiles de ingerir como carne, pollo" "Comidas reducidas a pures")))













(defrule REG-D11	
         (Diagnostico (idPaciente ?x)(enfermedad "Amigdalitis"))
         (or (Garganta (idPaciente ?x)(infeccion "Presencia de infeccion"))
             (or (Oido (idPaciente ?x)(estado "Infeccion"))
                 (Nariz (idPaciente ?x)(estado "Infeccion"))))
         ?h <- (Historial-Medico (idPaciente ?x)(tipoTratamiento "Ninguno"))
         ?t <- (Tratamiento (idPaciente ?X)(observaciones "Ninguno"))
 =>
    (modify ?h (tipoTratamiento "Medicacion analgesico-antibiotico" "Ingesta sumamente suave (liquidos y solidos)" "Reposo absoluto" "No realizar actividades"))
    (modify ?t (observaciones "Paciente no aliviado")))    
         







(defrule REG-D12
          (Diagnostico (idPaciente ?x)(enfermedad "Amigdalitis"))
          (Garganta (idPaciente ?x)(infeccion "Sin infeccion"))
          (Nariz (idPaciente ?x)(estado "Sin infeccion"))
          (Oido (idPaciente ?x)(estado "Sin infeccion"))
          ?h <- (Historial-Medico (idPaciente ?x)(tipoTratamiento "Ninguno"))
         ?t <- (Tratamiento (idPaciente ?X)(observaciones "Ninguno"))
 =>
    (modify ?h (tipoTratamiento "Medicacion analgesico" "Ingesta suave (liquidos y solidos)" "Reposo"))
    (modify ?t (observaciones "Paciente no aliviado")))    
         










(defrule REG-D1
                 (Paciente (dni ?x))
                 (or (Sintomas-Paciente (idPaciente ?x)(sintomas "Fiebre"))
                     (or (Sintomas-Paciente (idPaciente ?x)(sintomas "Dolor de cabeza"))
                         (Sintomas-Paciente (idPaciente ?x)(sintomas "Dolor de garganta"))))
                 (Sintomas-Paciente (idPaciente ?x)(descripcionMalestar "Garganta"))
                 (Amigdalas (idPaciente ?x)(tamanio "Grandes")(color "Rojiza"))
          =>
                 (assert(Garganta (idPaciente ?x)(amigdalas "Inflamada"))))





(defrule REG-D17
                 ?g <- (Garganta (idPaciente ?x)(amigdalas "Inflamada")(estado "Ninguno"))
         => 
                 (modify ?g (estado "Irritada")))








(defrule REG-D18
                 ?g <- (Garganta (idPaciente ?x)(estado "Irritada")(observaciones "Ninguno")(infeccion "Ninguno"))
                 (Amigdalas (idPaciente ?x)(placas-blancas "No hay"))
         => 
                 (modify ?g (infeccion "Sin infeccion")(examinacionAdicional "Oido" "Nariz"))
                 (printout t "No se presenta infeccion en la garganta pero deben analizarse tanto los oidos y nariz del paciente" clrf))
                 




(defrule REG-D2
                ?g <- (Garganta (idPaciente ?x)
                                (estado "Irritada")
                                (infeccion "Ninguno")
                                (estudioMedico  "Ninguno")
                                (examinacionAdicional "Ninguno"))
                      (Amigdalas (idPaciente ?x)
                                 (placas-blancas "Presentes"))
         => 
                (modify ?g (estudioMedico "Prueba Estreptococica")(examinacionAdicional "Oido" "Nariz")))





(defrule REG-D3
                (Estreptococo (idPaciente ?x)(estado "Negativo"))
                ?g <- (Garganta (idPaciente ?x)(estudioAdicional "Ninguno"))
        => 
          (modify ?g (estudioAdicional "Cultivo Faringeo")))







(defrule REG-D4
                (Cultivo-Faringeo (idPaciente ?x)(zona "Nariz")(resultado "Positivo"))
                ?n <- (Nariz (idPaciente ?x)(estado "Ninguno"))
        =>
             (modify ?n (estado "Infeccion")))






(defrule REG-D5
                (Cultivo-Faringeo (idPaciente ?x)(zona "Oido")(resultado "Positivo"))
                ?o <- (Oido (idPaciente ?x)(estado "Ninguno"))
        =>
            (modify ?o (estado "Infeccion")))







(defrule REG-D6
                (Estreptococo (idPaciente ?x)(estado "Positivo"))
                ?g <- (Garganta (idPaciente ?x)(infeccion "Ninguno"))
        => 
            (modify ?g (infeccion "Presencia de infeccion")))






(defrule REG-D7
                (Garganta (idPaciente ?x)(estado "Irritada"))   
        =>
              (assert (Diagnostico (idPaciente ?x)(zonaTratar "Garganta")(enfermedad "Amigdalitis"))))  










(defrule REG-D8
                (Diagnostico (idPaciente ?x)(enfermedad "Amigdalitis"))
                (Garganta (idPaciente ?x)(infeccion "Sin infeccion")(estado "Irritada"))
                (Oido (idPaciente ?x)(estado "Sin infeccion"))
                (Nariz (idPaciente ?x)(estado "Sin infeccion"))
                (Amigdalas (idPaciente ?x)(placas-blancas "No hay"))
        =>
            (assert(Receta (idPaciente ?x)
                           (analgesico "Ibuprofeno")
                           (liquidos "Zumo citrico" "Te con limon" "Abundante agua")
                           (solidos "Ensalada de frutas suaves" "Sopa Crema")))
            (assert (Tratamiento (idPaciente ?x)
                                 (tiempo "de 4 a 5 dias")
                                 (estado "En tratamiento")
                                 (recomendaciones "Reposo" "Realizar movimientos suaves sin fuerza")
                                 (visitas "Volver en 6 dias"))))




(defrule REG-D19
                (Diagnostico (idPaciente ?x)(enfermedad "Amigdalitis"))
                (Garganta (idPaciente ?x)(infeccion "Sin infeccion")(estado "Irritada"))
                (Oido (idPaciente ?x)(estado "Sin infeccion"))
                (Nariz (idPaciente ?x)(estado "Sin infeccion"))
                (Amigdalas (idPaciente ?x)(placas-blancas "Presentes"))
        =>
            (assert(Receta (idPaciente ?x)
                           (analgesico "Ibuprofeno")
                           (liquidos "Zumo citrico" "Te con limon" "Abundante agua")
                           (solidos "Ensalada de frutas suaves" "Sopa Crema")))
            (assert (Tratamiento (idPaciente ?x)
                                 (tiempo "de 4 a 5 dias")
                                 (estado "En tratamiento")
           (recomendaciones "Reposo" "Realizar movimientos suaves sin fuerza" "Realizar gargaras con agua tibia con sal (repetir varias veces al dia)")
                               (visitas "Volver en 6 dias"))))







(defrule REG-D9
                (Cultivo-Faringeo (idPaciente ?x)(zona "Garganta")(resultado "Positivo"))
                ?g <- (Garganta (idPaciente ?x)(infeccion "Ninguno"))
        => 
                (modify ?g (infeccion "Presencia de infeccion")))












(defrule REG-D10
                (Diagnostico (idPaciente ?x)(enfermedad "Amigdalitis"))
                (Amigdalas (idPaciente ?x)(placas-blancas "No hay"))
                (or (Garganta (idPaciente ?x)(infeccion "Presencia de infeccion"))
                    (or (Oido (idPaciente ?x)(estado "Infeccion"))
                        (Nariz (idPaciente ?x)(estado "Infeccion"))))
        => 
              (assert(Receta (idPaciente ?x)
                             (analgesico "Ibuprofeno")
                             (antibiotico "Amoxilina")
                             (liquidos "Zumo citrico" "Te con limon" "Bebidas calientes")
                             (solidos "Pure" "Comidas no dificiles de ingerir como carne, pollo" "Comidas reducidas a pures")))
              (assert (Tratamiento (idPaciente ?x)
                                   (tiempo "7 dias")
                                   (estado "En tratamiento")
             (recomendaciones "Reposo absoluto" "No realizar actividad fisica" "Realizar gargaras con agua tibia con sal (repetir varias veces al dia)")
                                   (visitas "Volver en 8 dias"))))






(defrule REG-D20  (Diagnostico (idPaciente ?x)(enfermedad "Amigdalitis"))
                  (Amigdalas (idPaciente ?x)(placas-blancas "Presentes"))
                  (or (Garganta (idPaciente ?x)(infeccion "Presencia de infeccion"))
                      (or (Oido (idPaciente ?x)(estado "Infeccion"))
                          (Nariz (idPaciente ?x)(estado "Infeccion"))))
        => 
              (assert(Receta (idPaciente ?x)
                             (analgesico "Ibuprofeno")
                             (antibiotico "Amoxilina")
                             (liquidos "Zumo citrico" "Te con limon" "Bebidas calientes")
                             (solidos "Pure" "Comidas no dificiles de ingerir como carne, pollo" "Comidas reducidas a pures")))
              (assert (Tratamiento (idPaciente ?x)
                                   (tiempo "7 dias")
                                   (estado "En tratamiento")
          (recomendaciones "Reposo absoluto" "No realizar actividad fisica" "Realizar gargaras con agua tibia con sal (repetir varias veces al dia)") 
                                   (visitas "Volver en 8 dias"))))





(defrule REG-D14
         (Cultivo-Faringeo (idPaciente ?x)(zona "Oido")(resultado "Negativo"))
         ?o <- (Oido (idPaciente ?x)(estado "Ninguno"))
 =>
      (modify ?o (estado "Sin infeccion")))





(defrule REG-D15
         (Cultivo-Faringeo (idPaciente ?x)(zona "Nariz")(resultado "Negativo"))
         ?n <- (Nariz (idPaciente ?x)(estado "Ninguno"))
 =>
      (modify ?n (estado "Sin infeccion")))



         

(defrule REG-D16
         (Cultivo-Faringeo (idPaciente ?x)(zona "Garganta")(resultado "Negativo"))
         ?n <- (Garganta (idPaciente ?x)(infeccion "Ninguno"))
 =>
      (modify ?n (infeccion "Sin infeccion")))






(defrule REG-D13
                (Sintomas-Paciente (idPaciente ?x)(descripcionMalestar "Garganta")(sintomas "Ninguno")(acotaciones "Ninguno"))
                (Garganta (idPaciente ?x)(amigdalas "Normal")(estado "No Irritada"))
                ?t <- (Tratamiento (idPaciente ?x)(estado "En tratamiento")(observaciones "Paciente no aliviado"))
                ?h <- (Historial-Medico (idPaciente ?x)(tipoTratamiento "Ninguno"))
  => 
            (modify ?t (estado "Terminado")(observaciones "Paciente aliviado")(visitas "Finalizado"))
            (modify ?h (tipoTratamiento "Terminado")))





(defrule REG-D21 
                  (Amigdalas (idPaciente ?x)(tamanio "Normal")(color "Normal")(placas-blancas "No hay"))
                   ?g <- (Garganta (idPaciente ?x)(amigdalas "Ninguno")(estado "Ninguno"))
 =>

                  (modify ?g (amigdalas "Normal")(estado "No Irritada")))




(defrule REG-D22  (Diagnostico (idPaciente ?x)(enfermedad "Amigdalitis"))
                  (Amigdalas (idPaciente ?x)(placas-blancas "Presentes"))
                  (or (Garganta (idPaciente ?x)(infeccion "Sin infeccion"))
                      (or (Oido (idPaciente ?x)(estado "Sin infeccion"))
                          (Nariz (idPaciente ?x)(estado "Sin infeccion"))))
        => 
               (assert(Receta (idPaciente ?x)
                           (analgesico "Ibuprofeno")
                           (liquidos "Zumo citrico" "Te con limon" "Abundante agua")
                           (solidos "Ensalada de frutas suaves" "Sopa Crema")))
            (assert (Tratamiento (idPaciente ?x)
                                 (tiempo "de 4 a 5 dias")
                                 (estado "En tratamiento")
           (recomendaciones "Reposo" "Realizar movimientos suaves sin fuerza" "Realizar gargaras con agua tibia con sal (repetir varias veces al dia)")
                               (visitas "Volver en 6 dias"))))






























       





















