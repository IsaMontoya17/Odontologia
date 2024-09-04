import { logicaAgregarPaciente } from './paciente.js';
import { logicaAgregarOdontologo } from './odontologo.js';
import { logicaEliminarOdontologo } from './odontologo.js';
import { logicaModificarOdontologo } from './odontologo.js';
import { logicaListarOdontologos } from './odontologo.js';
import { logicaEliminarPaciente } from './paciente.js';
import { logicaModificarPaciente } from './paciente.js';
import { logicaListarPacientes } from './paciente.js';
import { logicaAgregarTurno, logicaListarTurnos, logicaEliminarTurno } from './turno.js';


window.addEventListener("load", function () {

    document.querySelectorAll('.gestion').forEach(function (element) {
        element.addEventListener('mouseover', function () {
            this.classList.add('active');
        });

        element.addEventListener('mouseout', function () {
            this.classList.remove('active');
        });
    });

//VALIDACIONES TODO

    logicaAgregarOdontologo();
    logicaAgregarPaciente();
    logicaAgregarTurno();


    logicaEliminarOdontologo();
    logicaEliminarPaciente();
    logicaEliminarTurno();


    logicaModificarOdontologo();
    logicaModificarPaciente();


    logicaListarOdontologos();
    logicaListarPacientes();
    logicaListarTurnos();

});
