console.log("Contact");
// const baseUrl = "http://localhost:8080";
const baseUrl = `https://smartcontactmanager-emlp.onrender.com`
const viewContactModal = document.getElementById("view_contact_modal");

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    console.log("modal is shown");
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "view_contact_modal",
  override: true,
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
  contactModal.show();
}
function closeContactModal() {
  contactModal.hide();
}
async function loadContactData(id) {
  console.log(id);
  try {
    const data = await (await fetch(`${baseUrl}/api/contacts/${id}`)).json();
    console.log(data);

    document.querySelector("#contact_address").innerHTML = data.address;
    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
    document.querySelector("#contact_website").href = data.websiteLink;
    document.querySelector("#contact_linkedin").href = data.linkedInLink;
    document.querySelector("#contact_image").src = data.picture;
    const contWebLink = document.querySelector("#contact_website");
    if (data.websiteLink) {
      contWebLink.innerHTML = data.websiteLink;
    } else {
      contWebLink.innerHTML = "No website to show";
    }

    const contactLinkedinLink = document.querySelector("#contact_linkedin");
    if (data.linkedInLink) {
      contactLinkedinLink.innerHTML = data.linkedInLink;
    } else {
      contactLinkedinLink.innerHTML = "No website to show";
    }

    const contDesc = document.querySelector("#contact_description");
    if (data.description) {
      contDesc.innerHTML = data.description;
    } else {
      contDesc.innerHTML = "No description";
    }

    const contactFavrioute = document.querySelector("#contact_favrioute");
    if (data.favorite) {
      contactFavrioute.innerHTML =
        "This is Favrioute Contact <i class=' p-1 fa-solid fa-star' style='color: #FFD43B;'></i>";
    } else {
      contactFavrioute.innerHTML = "Not a Favrioute Contact";
    }
    contactModal.show();
  } catch (error) {
    console.log(error);
  }
}

// Delete Contact
async function deleteContact(id) {
  Swal.fire({
    title: "Are you sure?",
    text: "This action will permanently remove the contact.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Yes, delete it",
    cancelButtonText: "No, keep it",
    reverseButtons: true,
    buttonsStyling: false,
    background: document.documentElement.classList.contains("dark")
      ? "#1F2937"
      : "#ffffff",
    color: document.documentElement.classList.contains("dark")
      ? "#F9FAFB"
      : "#1F2937",
    customClass: {
      popup: "swal-elevated-modal",
      confirmButton:
        "inline-flex items-center justify-center min-w-[9rem] px-4 py-2 text-sm font-semibold text-white bg-red-500 rounded-xl shadow-lg shadow-red-500/40 hover:bg-red-600 focus:outline-none focus:ring-4 focus:ring-red-300 dark:focus:ring-red-800 transition",
      cancelButton:
        "inline-flex items-center justify-center min-w-[9rem] px-4 py-2 text-sm font-semibold text-gray-800 dark:text-gray-100 border border-gray-200 dark:border-gray-600 rounded-xl bg-gray-200 dark:bg-gray-800 shadow-md hover:bg-gray-100 dark:hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 transition",
      actions: "flex gap-3 justify-center w-full mt-6",
    },
  }).then((result) => {
    if (result.isConfirmed) {
      const url = `${baseUrl}/user/contacts/delete/${id}`;
      window.location.replace(url);
    }
  });
}
