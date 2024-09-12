document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('dentist-form');
    const nameInput = document.getElementById('name');
    const specialtyInput = document.getElementById('specialty');
    const emailInput = document.getElementById('email');
    const dentistList = document.getElementById('dentist-list');
    const apiUrl = 'http://tuapi.com/odontologos'; // Reemplaza con la URL de tu API

    // Cargar odontólogos al iniciar la página
    function loadDentists() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                dentistList.innerHTML = ''; // Limpiar la tabla
                data.forEach(addDentistToTable);
            });
    }

    // Añadir odontólogo
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        const dentistData = {
            name: nameInput.value,
            specialty: specialtyInput.value,
            email: emailInput.value
        };

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dentistData)
        })
        .then(response => response.json())
        .then(newDentist => {
            addDentistToTable(newDentist);
            form.reset(); // Limpiar el formulario
        })
        .catch(error => console.error('Error:', error));
    });

    // Mostrar odontólogos en la tabla
    function addDentistToTable(dentist) {
        const row = document.createElement('tr');
        row.id = dentist-${dentist.id};
        row.innerHTML = `
            <td class="name">${dentist.name}</td>
            <td class="specialty">${dentist.specialty}</td>
            <td class="email">${dentist.email}</td>
            <td>
                <button onclick="updateDentist(${dentist.id})">Actualizar</button>
                <button onclick="deleteDentist(${dentist.id})">Eliminar</button>
            </td>
        `;
        dentistList.appendChild(row);
    }

    // Actualizar odontólogo
    window.updateDentist = function(id) {
        const dentistRow = document.getElementById(dentist-${id});
        const name = prompt("Nuevo nombre:", dentistRow.querySelector('.name').textContent);
        const specialty = prompt("Nueva especialidad:", dentistRow.querySelector('.specialty').textContent);
        const email = prompt("Nuevo correo electrónico:", dentistRow.querySelector('.email').textContent);

        if (name && specialty && email) {
            const updatedDentist = { name, specialty, email };

            fetch(${apiUrl}/${id}, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedDentist)
            })
            .then(response => response.json())
            .then(updatedDentist => {
                dentistRow.querySelector('.name').textContent = updatedDentist.name;
                dentistRow.querySelector('.specialty').textContent = updatedDentist.specialty;
                dentistRow.querySelector('.email').textContent = updatedDentist.email;
            })
            .catch(error => console.error('Error:', error));
        }
    };

    // Eliminar odontólogo
    window.deleteDentist = function(id) {
        fetch(${apiUrl}/${id}, {
            method: 'DELETE'
        })
        .then(() => {
            document.getElementById(dentist-${id}).remove(); // Eliminar de la tabla
        })
        .catch(error => console.error('Error:', error));
    };

    // Cargar odontólogos al inicio
    loadDentists();
});