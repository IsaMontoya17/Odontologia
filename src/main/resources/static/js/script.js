import { logicaAgregarPaciente,logicaEliminarPaciente, logicaModificarPaciente, logicaListarPacientes, logicaBuscarPaciente } from './paciente.js';
import { logicaAgregarOdontologo, logicaEliminarOdontologo, logicaModificarOdontologo, logicaListarOdontologos, logicaBuscarOdontologo, logicaEliminarOdontologoPorMatricula } from './odontologo.js';
import { logicaAgregarTurno, logicaListarTurnos, logicaEliminarTurno, logicaBuscarTurnosPorFecha } from './turno.js';

window.addEventListener("load", function () {

    document.querySelectorAll('.gestion').forEach(function (element) {
        element.addEventListener('mouseover', function () {
            this.classList.add('active');
        });

        element.addEventListener('mouseout', function () {
            this.classList.remove('active');
        });
    });

    logicaAgregarOdontologo();
    logicaAgregarPaciente();
    logicaAgregarTurno();


    logicaEliminarOdontologo();
    logicaEliminarOdontologoPorMatricula();
    logicaEliminarPaciente();
    logicaEliminarTurno();


    logicaModificarOdontologo();
    logicaModificarPaciente();


    logicaListarOdontologos();
    logicaListarPacientes();
    logicaListarTurnos();

    logicaBuscarOdontologo();
    logicaBuscarPaciente();
    logicaBuscarTurnosPorFecha();

});
