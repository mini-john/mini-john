
var localisation=new Object();

function getLocalisation() {
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(success, error,
                {enableHighAccuracy: true, timeout: 60000, maximumAge: 600000});
        
    } else {
        console.log("Le Navigateur ne supporte pas la geolocalisation");
        localisation.error=-10;
        localisation.message="Le Navigateur ne supporte pas la geolocalisation";
    }
    return localisation;

}
function success(position) {
    localisation.latitude = position.coords.latitude;
    localisation.longitude = position.coords.longitude;
    localisation.altitude = position.coords.altitude;
    localisation.error = -1;
    $.ajax({
        type: "POST",
        url: "https://nominatim.openstreetmap.org/reverse?format=xml&lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&zoom=18&addressdetails=2",
        data: "whatever",
        dataType: "xml",
        async: false,
        success: function (xml) {
            var data = $('city', xml).text();
            localisation.ville = data;

        }
    });
    return localisation;
}

function error(error) {
    localisation.error = error.code;
    switch (error.code) {
        case error.PERMISSION_DENIED:
            localisation.message = "User denied the request for Geolocation";
            break;
        case error.POSITION_UNAVAILABLE:
            localisation.message = "Location information is unavailable.";
            break;
        case error.TIMEOUT:
            localisation.message = "The request to get user location timed out.";
            break;
        case error.UNKNOWN_ERROR:
            localisation.message = "An unknown error occurred.";
            break;
    }
}