const option = {
    enableHighAccuracy: false,
    maximumAge: 30000,
    timeout: 15000
}

const success = (position) => {
    let {latitude, longitude} = position.coords;
    document.getElementById("lat").value = latitude;
    document.getElementById("lnt").value = longitude;
    console.log("Initiated position!");
}

const handleException = (error) => {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            console.error("Geolocation 사용을 거부했습니다.");
            break;

        case error.POSITION_UNAVAILABLE:
            console.error("가져온 위치 정보를 사용할 수 없습니다.");
            break;

        case error.TIMEOUT:
            console.error("요청 시간이 초과했습니다.");
            break;

        case error.UNKNOWN_ERR:
            console.error("알 수 없는 에러가 발생했습니다.");
            break;
    }
}

const getPosition = () => {
    navigator.geolocation.getCurrentPosition(success, handleException, option);
}

