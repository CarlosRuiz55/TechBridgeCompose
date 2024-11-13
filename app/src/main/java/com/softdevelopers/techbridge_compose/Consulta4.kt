package com.softdevelopers.techbridge_compose

data class Consulta4(
    val employee_id: String,
    val nombres: String,
    val apellidos: String,
    val salario: Int,
    val participacion_proyectos: List<ParticipacionProyecto> // Esta es la lista que mencionas
)

data class ParticipacionProyecto(
    val project_id: String,
    val rol: String,
    val horas: Int,
    val tareas_completadas: Int,
    val comentarios: String
)

