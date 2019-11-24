var user;
var email_id;
var firebaseRef;
var Apps = "games";


firebase.auth().onAuthStateChanged(function(user) {
  if (user) {
    // User is signed in.

    document.getElementById("user_div").style.display = "block";
    document.getElementById("login_div").style.display = "none";

    user = firebase.auth().currentUser;

    if(user != null){

       firebaseRef = firebase.database().ref().child(Apps);
       email_id = user.email;
      document.getElementById("user_para").innerHTML = "Welcome User : " + email_id;


      firebaseRef.on("child_added",snap=>{
        var app_name = snap.child("appName").val();
        var company_name = snap.child("companyName").val();
        var email_ = snap.child("email").val();

        if(email_id==email_){
          var mylist = document.getElementById("myList");
          var node = document.createElement("LI");
          var textnode = document.createTextNode(app_name);
          node.appendChild(textnode);
            mylist.appendChild(node);
          }
      }
    );
    }

  } else {
    // No user is signed in.

    document.getElementById("user_div").style.display = "none";
    document.getElementById("login_div").style.display = "block";

  }
});

function submit(){
    
    appName = document.getElementById("AppName").value;
    companyName = document.getElementById("CompanyName").value;
    category = document.getElementById("Category").value;
    images = document.getElementById("Images").value;
    agerating = document.getElementById("Agerating").value;
    description = document.getElementById("Description").value;
    if(appName && companyName){
      var data = {
        appName:appName,
        companyName :companyName,
        email:email_id,
        category:category,
        image:images,
        agerating:agerating,
        description:description
      }
      Apps = document.getElementById("Category").value;
      firebaseRef = firebase.database().ref().child(Apps);
      
      firebaseRef.push(data);
    }

}

function login(){

  var userEmail = document.getElementById("email_field").value;
  var userPass = document.getElementById("password_field").value;

  firebase.auth().signInWithEmailAndPassword(userEmail, userPass).catch(function(error) {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;

    window.alert("Error : " + errorMessage);

    // ...
  });

}

function logout(){
  document.getElementById("myList").innerHTML = "";
 firebase.auth().signOut();


}
