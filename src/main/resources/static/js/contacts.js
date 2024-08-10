
console.log("Contact");
const baseUrl = "http://localhost:8080"
const viewContactModal = document.getElementById("view_contact_modal"); 

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: "view_contact_modal",
  override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal(){
    contactModal.show();
}
function closeContactModal(){
    contactModal.hide();
}
async function loadContactData(id){
    console.log(id)
    try {
        const data = await (
            await fetch (`${baseUrl}/api/contacts/${id}`)
        ).json();
        console.log(data);

        document.querySelector("#contact_address").innerHTML = data.address;
        document.querySelector("#contact_name").innerHTML = data.name;
        document.querySelector("#contact_email").innerHTML = data.email;
        document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
        document.querySelector("#contact_website").href = data.websiteLink;
        document.querySelector("#contact_linkedin").href = data.linkedInLink;
        document.querySelector("#contact_image").src = data.picture
        const contWebLink= document.querySelector("#contact_website")
        if(data.websiteLink){
            contWebLink.innerHTML= data.websiteLink
        }else{
            contWebLink.innerHTML = "No website to show"
        }

        const contactLinkedinLink= document.querySelector("#contact_linkedin")
        if(data.linkedInLink){
            contactLinkedinLink.innerHTML= data.linkedInLink
        }else{
            contactLinkedinLink.innerHTML = "No website to show"
        }

        const contDesc = document.querySelector("#contact_description")
        if(data.description){
            contDesc.innerHTML = data.description;
        }else{
            contDesc.innerHTML = "No description"
        }

        const contactFavrioute = document.querySelector("#contact_favrioute");
        if (data.favorite) {
            contactFavrioute.innerHTML = "This is Favrioute Contact <i class=' p-1 fa-solid fa-star' style='color: #FFD43B;'></i>";
        }else{
            contactFavrioute.innerHTML = "Not a Favrioute Contact"
        }
        contactModal.show();
    } catch (error) {
        console.log(error);
    }
    
}

// Delete Contact 
async function deleteContact(id){
    Swal.fire({
        title: "Are you sure?",
        text: "You want to this delete contact!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
            const url = `${baseUrl}/user/contacts/delete/${id}`;
            window.location.replace(url);
        }
      });
}