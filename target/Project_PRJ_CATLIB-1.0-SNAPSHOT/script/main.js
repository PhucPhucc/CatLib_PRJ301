/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function confirmBook(id, status) {
    let result;
    if (status === 'borrow') {
        result = confirm(`Are you sure you want to ${status} this book?\nFee: 5.000VND/day
Overdue Fee: 10.000VND/day`);
    } else if (status === 'return') {
        result = confirm(`Are you sure you want to ${status} this book?`);
    } else {
        result = false;
    }

    if (result) {
        window.location.href = `${window.location.hostname}/user/${status}?id=${id}`;
    } else {
        event.preventDefault();
    }

}

function triggerFileUpload() {
    document.getElementById('file-upload').click();
}

function previewImage(input) {
    if (input.files && input.files[0]) {
        const file = input.files[0];
        const reader = new FileReader();

        reader.onload = function (e) {
            document.getElementById("preview").src = e.target.result;
        };

        reader.readAsDataURL(file);

        // Gán tên file hoặc đường dẫn tùy ý
        document.getElementById("imagePath").value = file.name;
        console.log(file.name);
    }
}


const openModal = (id, book) => {
    const dialog = document.querySelector(id);
    dialog.showModal();
};
const closeModal = (id) => {
    const dialog = document.querySelector(id);
    dialog.close();
};

const successModal = (id) => {
    closeModal(id);
};

const openModalUpdate = (bookId) => {
    fetch(`${window.location.href}/update?id=${bookId}`)
            .then(res => res.json())
            .then(data => {
                console.log("Danh sách user:", data);
                const dialog = document.querySelector("#book");
                const title = document.querySelector("#title ");
                const publishDate = document.querySelector("#publishDate ");
                const publisher = document.querySelector("#publisher ");
                const category = document.querySelectorAll(".category");
                const author = document.querySelectorAll(".author");
                const stockQuantity = document.querySelector("#stockQuantity");
                const description = document.querySelector("#description ");
                const imageURL = document.querySelector("#preview");
                const btnAdd = document.querySelector("#btn-add");
                const form = document.querySelector("#form-book");
                dialog.showModal();
                title.value = data[0].title;
                publishDate.value = data[0].publishDate;
                publisher.value = data[0].publisher;

                stockQuantity.value = data[0].stockQuantity;
                description.value = data[0].description;
                imageURL.src = data[0].imageURL;

                category.forEach(e => {
                    if (e.textContent === data[0].categoryName) {
                        e.selected = true;
                    }
                });

                author.forEach(e => {
                    if (e.textContent === data[0].authorName) {
                        e.selected = true;
                    }
                });
                btnAdd.textContent = 'Update';
                form.action = window.location.href + '/update?id=' + bookId;

            })
            .catch(err => console.error("Lỗi khi fetch:", err));

};