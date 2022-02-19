// ベースマップを作成する
// ここでは3DのOpenStreetMapを表示する
var map =  new maplibregl.Map({
    container: 'map',
    style: 'style.json',
    center: [129.768337, 32.986804],
    zoom: 19,
    hash: true,
    pitch: 30,
    localIdeographFontFamily: false
})

// UIツール
// 右下のズームレベルの＋−ボタンを表示する
map.addControl(new maplibregl.NavigationControl(), 'bottom-right');
// 右上の現在位置の取得ボタンを表示する
map.addControl(new maplibregl.GeolocateControl({positionOptions: {enableHighAccuracy: true},trackUserLocation: true}), 'top-right');
// 左下の尺度を表示する
map.addControl(new maplibregl.ScaleControl() );

// 避難情報を取得するAPIのURL
const url = "https://ryota-terai.github.io/www/disaster_information.json";

// 画面がロードされたら地図にレイヤを追加する
map.on('load', function () {
    // 避難所情報レイヤを追加
    map.addSource('shelter_point', {
        type: 'geojson',
        data: './data/nagasaki_shelter.geojson'
    });
    map.loadImage(
        '/img/shelter.png',
        function (error, image) {
            if (error) throw error;
            map.addImage('shelter_icon', image);
        }
    );

    map.addLayer({
        'id': 'shelter_point',
		'type': 'symbol',
		'source': 'shelter_point',
		'layout': {
		'icon-image': 'shelter_icon',
		'icon-size': 0.1
        }
    });

    // 避難所情報レイヤを追加
    // TODO: APIからとってきたデータを指定したい
    //       現在はGeoJSON形式が有効でないと表示され、使えない
    map.addSource('disaster', {
        type: 'geojson',
        data: { "type": "FeatureCollection", "name": "Disaster Information", "crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } }, "features": [{ "type": "Feature", "properties": { "comment":"がけ崩れが発生していて、道路が通行できません"}, "geometry": { "type": "Point", "coordinates": [ 129.64678750160488, 32.93252441099267 ] } }, { "type": "Feature", "properties": { "comment":"火災発生"}, "geometry": { "type": "Point", "coordinates": [ 129.64102957448563, 32.932827570084726 ] } }, { "type": "Feature", "properties": { "comment":"教会で、火災発生6 Escape ***"}, "geometry": { "type": "Point", "coordinates": [ 129.64208701018111, 32.933548750025345 ] } }]}
    });

    map.loadImage(
        '/img/comment.png',
        function (error, image) {
            if (error) throw error;
            map.addImage('comment_icon', image);
        }
    );

    // スタイルを設定
    map.addLayer({
        'id': 'disaster',
		'type': 'symbol',
		'source': 'disaster',
		'layout': {
		'icon-image': 'comment_icon',
		'icon-size': 0.1
        }   
    });
});

// 避難所情報の地物をクリックしたときに、コメントを表示する
map.on('click', 'shelter_point', function (e) {
    console.log("click")
    
    var coordinates = e.features[0].geometry.coordinates.slice();
    var name = e.features[0].properties.name;
     
    while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
    coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
    }
    
    // ポップアップを表示する
    new maplibregl.Popup()
    .setLngLat(coordinates)
    .setHTML(name)
    .addTo(map);

    // 避難所情報欄に避難所名を記載する
    var shelterName = $("#shelter-name")[0];
    shelterName.innerHTML = name;
});

// 投稿情報の地物をクリックしたときに、コメントを表示する
map.on('click', 'disaster', function (e) {
    console.log("click")
    
    var coordinates = e.features[0].geometry.coordinates.slice();
    var comment = e.features[0].properties.comment;

    // コメントに改行コードが含まれている場合、改行タグに変換する
    if(comment.match('\n')){
        comment = comment.replace('\n', '<br>');
    }
     
    while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
    coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
    }
     
    new maplibregl.Popup()
    .setLngLat(coordinates)
    .setHTML(comment)
    .addTo(map);
});

// Change the cursor to a pointer when the mouse is over the places layer.
map.on('mouseenter', 'shelter_point', function () {
    map.getCanvas().style.cursor = 'pointer';
});
     
// Change it back to a pointer when it leaves.
map.on('mouseleave', 'shelter_point', function () {
    map.getCanvas().style.cursor = '';
});

// Change the cursor to a pointer when the mouse is over the places layer.
map.on('mouseenter', 'disaster', function () {
    map.getCanvas().style.cursor = 'pointer';
});
     
// Change it back to a pointer when it leaves.
map.on('mouseleave', 'disaster', function () {
    map.getCanvas().style.cursor = '';
});

/* // チェックボックスのオンオフでレイヤの表示/非表示を切り替える

$(#shelter-layer).click(function(){
    if(!$(this).prop('checked')){
        map.removeLayer('shelter_point');
    }
}); */