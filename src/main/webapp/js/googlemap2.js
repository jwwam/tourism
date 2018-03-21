// When the window has finished loading create our google map below
google.maps.event.addDomListener(window, 'load', googlemapinit);

function googlemapinit() {
    // Basic options for a simple Google Map
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions

    var mapOptions = {
        // How zoomed in you want the map to start at (always required)
        zoom: 11,

        // The latitude and longitude to center the map (always required)
        center: new google.maps.LatLng(50.100499598, 14.255998976),



        // How you would like to style the map.
        styles: [{"featureType":"landscape.natural","elementType":"geometry.fill","stylers":[{"visibility":"on"},{"color":"#e0efef"}]},{"featureType":"poi","elementType":"geometry.fill","stylers":[{"visibility":"on"},{"hue":"#1900ff"},{"color":"#c0e8e8"}]},{"featureType":"road","elementType":"geometry","stylers":[{"lightness":100},{"visibility":"simplified"}]},{"featureType":"road","elementType":"labels","stylers":[{"visibility":"off"}]},{"featureType":"transit.line","elementType":"geometry","stylers":[{"visibility":"on"},{"lightness":700}]},{"featureType":"water","elementType":"all","stylers":[{"color":"#7dcdcd"}]}]
    };

    // Get the HTML DOM element that will contain your map
    // We are using a div with id="google_map" seen below in the <body>
    var mapElement = document.getElementById('google_map2');

    // Create the Google Map using our element and options defined above
    var map = new google.maps.Map(mapElement, mapOptions);

    // Let's also add a marker while we're at it
    // var gimage = 'images/gmarker.png';

    // var marker = new google.maps.Marker({
    //     position: new google.maps.LatLng(40.6700, -73.9400),
    //     map: map,
    //     icon: gimage,
    //     title: 'Google Map Title'
    // });
}
