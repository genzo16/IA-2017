(deftemplate response (slot var_a))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Diagnostico Automatizado en Pediatría
;;

(deftemplate paciente
	(slot nombre
		(type STRING)
	)
	(slot apellido
		(type STRING)
	)
	(slot estado 
		(type STRING)
		(allowed-strings "Aliviado" "Adolorido" "Curado")
	)
	(slot zona-afectada
		(type STRING)
		(allowed-strings "Garganta" "Oido" "Nariz")
	)
	(slot derivaciones
		(type STRING)
		(allowed-strings "Enfermedad Renal" "Absceso Periamigdalino")
	)
)

(deftemplate garganta
	(slot estado
		(type STRING)
		(allowed-strings "Irritada" "No Irritada")
	)
	(slot dolor
		(type STRING)
		(allowed-strings "Leve" "Fuerte" "Ninguno")
	)
	(slot amigdalas
		(type STRING)
		(allowed-strings "Rojiza" "Inflamada" "Normal")
	)
	(slot estudio-medico
		(type STRING)
		(allowed-strings "Prueba Estreptococica" "Cultivo Faringeo")
	)
	(slot estreptococo
		(type STRING)
		(allowed-strings "Positivo" "Negativo")
	)
	(slot cultivo-faringeo
		(type STRING)
		(allowed-strings "Positivo" "Negativo")
	)
	(slot examinacion-adicional
		(type STRING)
		(allowed-strings "Oido" "Nariz")
	)
	(slot observaciones
		(type STRING)
	)
)

(deftemplate cultivo-faringeo
	(slot zona
		(type STRING)
		(allowed-strings "Garganta")
	)
	(slot estado
		(type STRING)
		(allowed-strings "Positivo" "Negativo")
	)
	(slot colonizacion-bacteriana
		(type STRING)
		(allowed-strings "Estreptococo" "Ninguno")
	)
)

(deftemplate oido
	(slot examinacion
		(type STRING)
		(allowed-strings "Positivo" "Negativo")
	)
	(slot estado
		(type STRING)
		(allowed-strings "Infeccion" "Sin Infeccion")
	)
)

(deftemplate nariz
	(slot examinacion
		(type STRING)
		(allowed-strings "Positivo" "Negativo")
	)
	(slot estado
		(type STRING)
		(allowed-strings "Infeccion" "Sin Infeccion")
	)
)

(deftemplate diagnostico
	(slot enfermedad
		(type STRING)
		(allowed-strings "Amigdalitis")
	)
	(slot fecha
		(type STRING)
	)
	(slot zona-a-tratar
		(type STRING)
		(allowed-strings "Garganta")
	)
)

(deftemplate receta
	(slot antibiotico
		(type STRING)
		(allowed-strings "Amoxicilina" "Penicilina")
	)
	(slot analgesico
		(type STRING)
		(allowed-strings "Ibuprofeno")
	)
	(slot liquidos
		(type STRING)
		(allowed-strings "Sopa" "Zumo citrico" "te con limon")
	)
	(slot solidos
		(type STRING)
		(allowed-strings "Pure")
	)
)	

(deftemplate tratamiento
	(slot tiempo
		(type STRING)
		(allowed-strings "4 dias")
	)
	(slot estado
		(type STRING)
		(allowed-strings "En Tratamiento" "Terminado")
	)
	(slot recomendaciones
		(type STRING)
		(allowed-strings "Reposo" "Reposo Absoluto")
	)
	(slot visitas
		(type STRING)
		(allowed-strings "Volver en 4 dias" "Finalizado")
	)
)

(deftemplate historial-medico
	(slot idPaciente
		(type STRING)
	)
	(slot fecha-enfermedades
		(type STRING)
	)
	(slot fecha-tratamietno-inicializado
		(type STRING)
	)
	(slot fecha-tratamiento-finalizado
		(type STRING)
	)
	(slot tipos-tratamiento
		(type STRING)
		(allowed-strings "Medicacion Analgesico" "Ingesta Suaves en Liquidos y Solidos")
	)
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; RULES


(defrule REG-D1
	"Paciente en estado dolorido"
	(paciente (nombre ?n))
	(garganta (dolor "Leve" | "Fuerte"))
	=>
	(assert (paciente (nombre ?n) 
					  (estado "Adolorido")
			)
	)
	(printout t "El estado del paciente es adolorido")
)

(defrule REG-D2
	"Paciente zona afectada garganta"
	(paciente (zona-afectada "Garganta"))
	=>
	(assert (diagnostico (zona-a-tratar "Garganta")
			)
	)
	(printout t "La zona a tratar es la garganta")
)

(defrule REG-D3
	"Amigdalas rojas e inflamadas, garganta irritada"
	(garganta (amigdalas "Rojiza" | "Inflamada"))
	=>
	(assert (garganta (estado "Irritada")
			)
	)
	(printout t "La garganta esta irritada")
)

(defrule REG-D4
	"Garganta irritada, prueba estreptococo"
	(garganta (estado "Irritada"))
	=>
	(assert (garganta (estudio-medico "Prueba Estreptococica")
			)
	)
)

(defrule REG-D5
	"Examen oido negativo, sin infeccion"
	(and (garganta (examinacion-adicional "Oido"))
		 (oido (examinacion "Negativo"))
	)
	=>
	(assert (oido (estado "Sin Infeccion")
			)
	)
)


(defrule REG-D6
	"Examen nariz negativo, sin infeccion"
	(and (garganta (examinacion-adicional "Nariz"))
		 (nariz (examinacion "Negativo"))
	)
	=>
	(assert (nariz (estado "Sin Infeccion")
			)
	)
)

(defrule REG-D7
	"Estudio medico estreptococo negativo, realizar estudio de cultivo faringeo"
	(and (garganta (estudio-medico "Prueba Estreptococica"))
		 (garganta (estreptococo "Negativo"))
	)
	=>
	(assert (garganta (estudio-medico "Cultivo Faringeo")))
	(assert (cultivo-faringeo (zona "Garganta")))
)

(defrule REG-D8
	"Cultivo faringeo positivo, colonizacion bacteriana"
	(cultivo-faringeo (estado "Positivo"))
	=>
	(assert (cultivo-faringeo (colonizacion-bacteriana "Estreptococo")))
)

(defrule REG-D9
	"Cultivo faringeo negativo, colonizacion bacteriana ninguna"
	(cultivo-faringeo (estado "Positivo"))
	=>
	(assert (cultivo-faringeo (colonizacion-bacteriana "Ninguno")))
)

(defrule REG-D10
	"Paciente dolorido, garganta irritada, odio y nariz sin infeccion, estreptococo y cultivo faringeo negativo. Diagnostico: Amigdalitis simple"
	(paciente (estado "Adolorido"))
	(paciente (zona-afectada "Garganta"))
	(garganta (estado "Irritada"))
	(oido (estado "Sin Infeccion"))
	(nariz (estado "Sin Infeccion"))
	(garganta (estreptococo "Negativo"))
	(garganta (cultivo-faringeo "Negativo"))
	=>
	(assert (diagnostico (enfermedad "Amigdalitis")))
)

(defrule REG-D11
	"Amigdalitis, receta analgesico"
	(diagnostico (enfermedad "Amigdalitis"))
	=>
	(assert (receta (analgesico "Ibuprofeno")))
)

(defrule REG-D12
	"Amigdalitis, receta alimentos suaves y liquidos calientes"
	(diagnostico (enfermedad "Amigdalitis"))
	=>
	(assert (receta (solidos "Pure")))
)

(defrule REG-D13
	"Amigdalitis, tiempo tratamiento 4 dias, reposo, volver"
	(diagnostico (enfermedad "Amigdalitis"))
	=>
	(assert (tratamiento (tiempo "4 dias")))
	(assert (tratamiento (estado "En Tratamiento")))
	(assert (tratamiento (recomendaciones "Reposo")))
	(assert (tratamiento (visitas "Volver en 4 dias")))
)

