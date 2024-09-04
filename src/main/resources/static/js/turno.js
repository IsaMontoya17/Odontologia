//-----------------------------------Registrar-------------------------------------------------------------------------
export function formRegistrarTurno() {
    document.querySelector('main').innerHTML = `
        <div class="card">
            <h1>Registrar Turno</h1>
            <form id="registrar-turno-form">
                <div>
                    <label for="odontologo">Odontólogo:</label>
                    <select id="odontologo" name="odontologo" required>
                        <option value="" disabled selected>Seleccione un odontólogo</option>
                    </select>
                </div>
                <div>
                    <label for="paciente">Paciente:</label>
                    <select id="paciente" name="paciente" required>
                        <option value="" disabled selected>Seleccione un paciente</option>
                    </select>
                </div>
                <div>
                    <label for="fecha">Fecha:</label>
                    <input type="date" id="fecha" name="fecha" required>
                </div>
                <div>
                    <label for="hora">Hora:</label>
                    <input type="time" id="hora" name="hora" required>
                </div>
                <div>
                    <button type="submit">Registrar Turno</button>
                </div>
            </form>
            <div id="response" style="display:none; margin-top:10px"></div>
        </div>
    `;

    // Llamadas para llenar los selectores de odontólogos y pacientes
    cargarOdontologos();
    cargarPacientes();

    // Asignación de lógica al formulario después de que esté en el DOM
    asignarEventoFormulario();
}

function cargarOdontologos() {
    const odontologoSelect = document.getElementById('odontologo');
    if (odontologoSelect) {
        fetch('/odontologos')  // Ajusta la URL al endpoint correspondiente en tu backend
            .then(response => response.json())
            .then(data => {
                data.forEach(odontologo => {
                    const option = document.createElement('option');
                    option.value = odontologo.id;
                    option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
                    odontologoSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error al cargar odontólogos:', error));
    }
}

function cargarPacientes() {
    const pacienteSelect = document.getElementById('paciente');
    if (pacienteSelect) {
        fetch('/pacientes')  // Ajusta la URL al endpoint correspondiente en tu backend
            .then(response => response.json())
            .then(data => {
                data.forEach(paciente => {
                    const option = document.createElement('option');
                    option.value = paciente.id;
                    option.textContent = `${paciente.nombre} ${paciente.apellido}`;
                    pacienteSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error al cargar pacientes:', error));
    }
}

function asignarEventoFormulario() {
    const registrarForm = document.getElementById("registrar-turno-form");
    if (registrarForm) {
        registrarForm.onsubmit = function (e) {
            e.preventDefault();

            const pacienteId = document.getElementById('paciente').value.trim();
            const odontologoId = document.getElementById('odontologo').value.trim();
            const fecha = document.getElementById('fecha').value;
            const hora = document.getElementById('hora').value;

            fetch('/turnos', {  // Asegúrate de que esta URL sea correcta
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    odontologoId: odontologoId,
                    pacienteId: pacienteId,
                    fecha: fecha,
                    hora: hora
                })
            })
            .then(response => {
                if (response.ok) {
                    // Limpiar el formulario en caso de éxito
                    registrarForm.reset();
                    mostrarMensaje('Turno asignado con éxito', 'success');
                } else {
                    // Manejar errores de la solicitud
                    response.json().then(data => {
                        mostrarMensaje(data.message || 'Turno no disponible', 'error');
                    }).catch(() => {
                        mostrarMensaje('Turno no disponible', 'error');
                    });
                }
            })
            .catch(error => {
                // Mostrar error de red
                mostrarMensaje('Error al asignar turno', 'error');
            })
            .finally(() => {
                // Limpia el formulario después de cada intento
                registrarForm.reset();
            });
        };
    }
}

function mostrarMensaje(mensaje, tipo) {
    const responseDiv = document.getElementById('response');
    responseDiv.style.display = 'block';
    responseDiv.innerHTML = mensaje;
    responseDiv.style.color = tipo === 'error' ? 'red' : 'green';
}

export function logicaAgregarTurno() {
    document.getElementById('asignar-turno').addEventListener('click', function () {
        // Renderizar el formulario para registrar un turno
        formRegistrarTurno();  // Renderiza el formulario
    });
}


//----------------------------------------Listar---------------------------------------------------------------
export function formListarTurnos() {
    document.querySelector('main').innerHTML = `
        <div class="card">
            <h1>Listado de Turnos</h1>
            <div id="lista-turnos"></div>
            <div id="response" style="display:none; margin-top:10px"></div>
        </div>
    `;

    // Llamada para cargar y mostrar los turnos
    cargarTurnos();
}

function cargarTurnos() {
    fetch('/turnos')  // Ajusta la URL al endpoint correspondiente en tu backend
        .then(response => response.json())
        .then(data => {
            const listaTurnos = document.getElementById('lista-turnos');
            listaTurnos.innerHTML = ''; // Limpiar la lista antes de llenarla

            if (data && data.length > 0) {
                let turnosHTML = '<ul>';

                data.forEach(turno => {
                    turnosHTML += `
                        <li>
                            <strong>ID:</strong> ${turno.id} <br>
                            <strong>Odontólogo:</strong> ${turno.odontologo.nombre} ${turno.odontologo.apellido} <br>
                            <strong>Paciente:</strong> ${turno.paciente.nombre} ${turno.paciente.apellido} <br>
                            <strong>Fecha:</strong> ${turno.fecha} <br>
                            <strong>Hora:</strong> ${turno.hora}
                        </li><hr>`;
                });

                turnosHTML += '</ul>';
                listaTurnos.innerHTML = turnosHTML;
            } else {
                listaTurnos.innerHTML = '<p>No hay turnos registrados</p>';
            }
        })
        .catch(error => {
            let errorAlert = '<div class="alert alert-danger alert-dismissible" style="background:rgba(255, 0, 0, 0.2);padding:.5em 1em;color: red;margin: .5em 0; padding: 10px; border-radius: 5px;">' +
                '<p>Error al listar los turnos, intente nuevamente</p></div>';

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";

            setTimeout(() => {
                document.querySelector('#response').style.display = "none";
            }, 4000);
        });
}

export function logicaListarTurnos() {
    document.getElementById('listar-turnos').addEventListener('click', function () {
        // Renderizar la lista de turnos
        formListarTurnos();
    });
}


//-------------------------------------------------------Eliminar----------------------------------------------------------

export function formEliminarTurno() {
    document.querySelector('main').innerHTML = `
        <div class="card">
            <h1>Eliminar Turno</h1>
            <form id="eliminar-turno-form">
                <div>
                    <label for="id">ID del Turno:</label>
                    <input type="text" id="id" name="id" required>
                </div>
                <div>
                    <button type="submit">Eliminar</button>
                </div>
            </form>
            <div id="response" style="display:none; margin-top:10px"></div>
        </div>
    `;
}

export function logicaEliminarTurno() {
    const eliminarButton = document.getElementById('eliminar-turno');
    if (eliminarButton) {
        eliminarButton.addEventListener('click', function () {
            formEliminarTurno();

            document.getElementById("eliminar-turno-form").onsubmit = function (e) {
                e.preventDefault();
                eliminarTurno();
            };

            function eliminarTurno() {
                const id = document.querySelector('#id').value;

                // Verifica que el ID no esté vacío
                if (!id) {
                    console.error('ID is required');
                    return;
                }

                const url = `/turnos/${id}`;
                const settings = {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                };

                fetch(url, settings)
                    .then(response => {
                        if (response.ok) {
                            let successAlert = '<div class="alert alert-success alert-dismissible" style="background:rgba(0, 255, 0, 0.2);padding:.5em 1em;color: green; margin: .5em 0; padding: 10px; border-radius: 5px;">' +
                                '<p>Turno eliminado exitosamente</p></div>';

                            document.querySelector('#response').innerHTML = successAlert;
                            document.querySelector('#response').style.display = "block";
                            resetEliminarForm();

                            setTimeout(() => {
                                document.querySelector('#response').style.display = "none";
                            }, 4000);
                        } else {
                            throw new Error('Error en la eliminación');
                        }
                    })
                    .catch(error => {
                        let errorAlert = '<div class="alert alert-danger alert-dismissible" style="background:rgba(255, 0, 0, 0.2);padding:.5em 1em;color: red;margin: .5em 0; padding: 10px; border-radius: 5px;">' +
                            '<p>Error al eliminar el turno, intente nuevamente</p></div>';

                        document.querySelector('#response').innerHTML = errorAlert;
                        document.querySelector('#response').style.display = "block";
                        resetEliminarForm();

                        setTimeout(() => {
                            document.querySelector('#response').style.display = "none";
                        }, 4000);
                    });
            }

            function resetEliminarForm() {
                document.querySelector('#id').value = "";
            }
        });
    } else {
        console.error('Element with id "eliminar-turno" not found');
    }
}



