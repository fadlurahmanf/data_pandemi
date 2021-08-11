package com.fadlurahmanf.datapandemi.extenson

data class IdProvinsi(
    var id:String,
    var name:String,
    var lat:Double,
    var lng:Double
)

class DummyProvinsi {
    fun getDummyAllProvince():ArrayList<IdProvinsi>{
        var list = ArrayList<IdProvinsi>()
        list.add(IdProvinsi("11","ACEH", 4.36855, 97.0253))
        list.add(IdProvinsi("12","SUMATERA UTARA", 2.19235, 99.38122))
        list.add(IdProvinsi("13","SUMATERA BARAT", -1.34225, 100.0761))
        list.add(IdProvinsi("14","RIAU", 0.50041, 101.54758))
        list.add(IdProvinsi("15","JAMBI", -1.61157, 102.7797))
        list.add(IdProvinsi("16","SUMATERA SELATAN", -3.12668, 104.09306))
        list.add(IdProvinsi("17","BENGKULU", -3.51868, 102.53598))
        list.add(IdProvinsi("18","LAMPUNG", -4.8555, 105.0273))
        list.add(IdProvinsi("19","KEPULAUAN BANGKA BELITUNG", -2.75775, 107.58394))
        list.add(IdProvinsi("21","KEPULAUAN RIAU", -0.15478, 104.58037))
        list.add(IdProvinsi("31","DKI JAKARTA", -6.2, 106.816666))
        list.add(IdProvinsi("32","JAWA BARAT", -6.88917, 107.64047))
        list.add(IdProvinsi("33","JAWA TENGAH", -7.30324, 110.00441))
        list.add(IdProvinsi("34","DI YOGYAKARTA", 7.7956, 110.3695))
        list.add(IdProvinsi("35","JAWA TIMUR", -6.96851, 113.98005))
        list.add(IdProvinsi("36","BANTEN", -6.44538, 106.13756))
        list.add(IdProvinsi("51","BALI", -8.23566, 115.12239))
        list.add(IdProvinsi("52","NUSA TENGGARA BARAT", -8.12179, 117.63696))
        list.add(IdProvinsi("53","NUSA TENGGARA TIMUR", -8.56568, 120.69786))
        list.add(IdProvinsi("61","KALIMANTAN BARAT", -0.13224, 111.09689))
        list.add(IdProvinsi("62","KALIMANTAN TENGAH", -1.49958, 113.29033))
        list.add(IdProvinsi("63","KALIMANTAN SELATAN", -2.94348, 115.37565))
        list.add(IdProvinsi("64","KALIMANTAN TIMUR", 0.78844, 116.242))
        list.add(IdProvinsi("65","KALIMANTAN UTARA",  2.72594, 116.911))
        list.add(IdProvinsi("71","SULAWESI UTARA",  0.65557, 124.09015))
        list.add(IdProvinsi("72","SULAWESI TENGAH",  -1.69378, 120.80886))
        list.add(IdProvinsi("73","SULAWESI SELATAN",  -3.64467, 119.94719))
        list.add(IdProvinsi("74","SULAWESI TENGGARA",  -3.54912, 121.72796))
        list.add(IdProvinsi("75","GORONTALO",  0.71862, 122.45559))
        list.add(IdProvinsi("76","SULAWESI BARAT",  -2.49745, 119.3919))
        list.add(IdProvinsi("81","MALUKU",  -3.11884, 129.42078))
        list.add(IdProvinsi("82","MALUKU UTARA",  0.63012, 127.97202))
        list.add(IdProvinsi("91","PAPUA BARAT",  -1.38424, 132.90253))
        list.add(IdProvinsi("94","PAPUA",  -3.98857, 138.34853))
        return list
    }
}