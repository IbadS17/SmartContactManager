console.log("Script loaded");

let currentTheme = getTheme();
changeTheme();

// TODO:
function changeTheme(){
    console.log(currentTheme)
    document.querySelector("html").classList.add(currentTheme);
    // Set the listener to change the theme button
    
    const changeThemeButton = document.querySelector("#theme_change_btn")
    changeThemeButton.querySelector("span").textContent= currentTheme == "light" ? " Dark" : " Light";
    changeThemeButton.addEventListener("click", (event) => {
        const oldTheme = currentTheme;
        if(currentTheme == 'dark'){
            currentTheme  = "light";
        } else {
            currentTheme = "dark";
        }

        // Update to Local Storage
        setTheme(currentTheme);

        // remove the current theme
        document.querySelector("html").classList.remove(oldTheme);

        // Set the Current Theme
        document.querySelector("html").classList.add(currentTheme);

        // Change the text of button 
        changeThemeButton.querySelector("span").textContent = 
        currentTheme == "light" ? " Dark" : " Light";

    });

}

// Set theme to local storage 
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

// Get Theme from Local Storage 
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}


// Show Password and Hide Password

// const showPassword = () => {
//     console.log("Button Cliked Hied")
//     passwordRef.current.type = "text"
//     if (ref.current.src.includes("img/hide.png")) {
//         ref.current.src = "img/eye.png"
//         passwordRef.current.type = "password"
//     }
//     else {
//         ref.current.src = "img/hide.png"
//         // passwordRef.current.type="password"
//     }

// }

