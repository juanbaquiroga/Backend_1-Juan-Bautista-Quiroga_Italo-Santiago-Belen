document.addEventListener("DOMContentLoaded", () => {
    const odontologosContainer = document.getElementById("odontologosContainer");
    const odontologoForm = document.getElementById("odontologoForm");

    // Endpoint de la API de JSON Server
    const apiURL = "http://localhost:3000/odontologos";

    // Función para obtener la lista de odontólogos
    function fetchOdontologos() {
        fetch(apiURL)
            .then(response => response.json())
            .then(odontologos => {
                odontologosContainer.innerHTML = ""; 
                odontologos.forEach(odontologo => {

                    const odontologoDiv = document.createElement("div");
                    odontologoDiv.classList.add("odontologo");
                    odontologoDiv.innerHTML = `
                        <p><strong>Nombre:</strong> ${odontologo.nombre}</p>
                        <p><strong>Especialidad:</strong> ${odontologo.especialidad}</p>
                        <p><strong>Paciente:</strong> ${odontologo.paciente}</p>
                        <p><strong>Contacto:</strong> ${odontologo.contacto}</p>
                        <p><strong>Fecha de Turno:</strong> ${odontologo.turno}</p>
                        <button class="delete-btn" data-id="${odontologo.id}">Eliminar</button>
                    `;
                    odontologosContainer.appendChild(odontologoDiv);
                });


                const deleteButtons = document.querySelectorAll(".delete-btn");
                deleteButtons.forEach(button => {
                    button.addEventListener("click", eliminarOdontologo);
                });
            })
            .catch(error => console.error("Error al obtener los odontólogos:", error));
    }

    // Función para eliminar un odontólogo
    function eliminarOdontologo(event) {
        const odontologoId = event.target.getAttribute("data-id");

        fetch(`${apiURL}/${odontologoId}`, {
            method: "DELETE",
        })
        .then(() => {
            console.log(`Odontólogo con ID ${odontologoId} eliminado.`);
            fetchOdontologos(); 
        })
        .catch(error => console.error("Error al eliminar el odontólogo:", error));
    }


    odontologoForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const nuevoOdontologo = {
            nombre: document.getElementById("nombre").value,
            especialidad: document.getElementById("especialidad").value,
            paciente: document.getElementById("paciente").value,
            contacto: document.getElementById("contacto").value,
            turno: document.getElementById("turno").value
        };

        fetch(apiURL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(nuevoOdontologo)
        })
        .then(response => response.json())
        .then(() => {
            console.log("Odontólogo añadido exitosamente.");
            odontologoForm.reset(); 
            fetchOdontologos(); 
        })
        .catch(error => console.error("Error al añadir el odontólogo:", error));
    });

    // Llamar a la función para obtener la lista de odontólogos cuando se carga la página
    fetchOdontologos();
});