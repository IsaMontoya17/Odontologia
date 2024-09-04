import { logicaAgregarPaciente } from './paciente.js';
import { logicaAgregarOdontologo } from './odontologo.js';
import { logicaEliminarOdontologo } from './odontologo.js';
import { logicaModificarOdontologo } from './odontologo.js';
import { logicaListarOdontologos } from './odontologo.js';
import { logicaEliminarPaciente } from './paciente.js';
import { logicaModificarPaciente } from './paciente.js';
import { logicaListarPacientes } from './paciente.js';

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

// ---------- agregar --------------
    logicaAgregarOdontologo();
    logicaAgregarPaciente();

// ---------- eliminar -------------- TODO
    logicaEliminarOdontologo();
    logicaEliminarPaciente();

// ---------- actualizar -------------- TODO
    logicaModificarOdontologo();
    logicaModificarPaciente();

// ---------- buscar -------------- TODO


// ---------- listar -------------- TODO
    logicaListarOdontologos();
    logicaListarPacientes();

});
