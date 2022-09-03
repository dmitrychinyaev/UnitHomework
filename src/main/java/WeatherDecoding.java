public class WeatherDecoding {
    public void printWeather(String[] weather){
        if (weather.length < 4){
            System.out.println("Недостаточно данных для расшифровки");
        }
        System.out.println(checkWind(weather[0],weather[1]));
        System.out.println(checkClouds(weather[2]) + checkCloudsBase(weather[3]));
    }

    public String checkWind(String direction, String speed) {
        int checkedDirection = 0;
        int checkedSpeed = 0;
// Проверяем данные ветра. Ветер считается в градусах.
// Если направление больше 360 или меньше 0 - некорректные данные
        try {
            checkedDirection = Integer.parseInt(direction);
            if (checkedDirection > 360 || checkedDirection < 0) {
                throw new DirectionMismatchException();
            }
        } catch (NumberFormatException e) {
            throw new DirectionMismatchException();
        }
// Проверяем данные скорости ветра. Скорость ветра не бывает отрицательной.
        try {
            checkedSpeed = Integer.parseInt(speed);
            if (checkedSpeed < 0) {
                throw new SpeedMismatchException();
            }
        } catch (NumberFormatException e) {
            throw new SpeedMismatchException();
        }

        return "Ветер " + checkedDirection + " градусов, " + checkedSpeed + " м/с. ";
    }

    public String checkClouds(String cloud) {
        String checkedCloud = null;
//Проверяем соответствие данных с универсальной кодированной информацией используемой в прогнозах
        try {
            if (AmountOfClouds.valueOf(cloud).equals(AmountOfClouds.BKN)) {
                checkedCloud = "Значительные разорванные облака ";
            } else if (AmountOfClouds.valueOf(cloud).equals(AmountOfClouds.SCT)) {
                checkedCloud = "Разбросанные облака ";
            } else if (AmountOfClouds.valueOf(cloud).equals(AmountOfClouds.FEW)) {
                checkedCloud = "Незначительные облака ";
            } else if (AmountOfClouds.valueOf(cloud).equals(AmountOfClouds.OVC)) {
                checkedCloud = "Сплошная облачность ";
            }
        } catch (IllegalArgumentException e) {
            throw new CloudMismatchException();
        }
        return checkedCloud;
    }

    public String checkCloudsBase(String height){
        int checkedHeight = 0;
//Проверяем значение нижней границы облаков
        try {
            checkedHeight = Integer.parseInt(height);
            if (checkedHeight < 0) {
                throw new CloudBaseMismatchException();
            }
        } catch (NumberFormatException e) {
            throw new CloudBaseMismatchException();
        }
//В прогнозах высота нижней границы даётся в футах в трех цифрах без последнего нуля.
// Например, BKN210 означает - значительные разорванные облака на 2100 футов
// Переводим её в метры.
        checkedHeight = checkedHeight * 30;
        return checkedHeight + " метров";
    }
}
