/*
    Author: Sincere Warren
    File Name: script.js
    Date: December 5, 2024
*/

// Hamburger Menu Function
function hamburger() {
    var navlinks = document.getElementById("menu-links");
    var menuicon = document.getElementById("icon")
    if (navlinks.style.display === "block") {
        navlinks.style.display = "none";
        navlinks.style.color = "#04060D";
    } else {
        navlinks.style.display = "block";
        navlinks.style.color = "#143AA2";
    }
}