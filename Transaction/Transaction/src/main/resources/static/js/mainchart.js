google.charts.load('current', { packages: ['corechart', 'bar'] });
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {
    fetch('/api/transactions')  // /api/transactions에서 데이터 요청
        .then(response  => response.json())  // JSON 형식으로 데이터 파싱
        .then(data => {
            var chartData = new google.visualization.DataTable();
            chartData.addColumn('string', 'date'); // 날짜 컬럼
            chartData.addColumn('number', 'amount'); // 금액 컬럼

            // 데이터 변환 (JavaScript 배열 형태로 변환)
            data.forEach(myList => {
                chartData.addRow([myList.date, myList.amount]);
            });

            var options = {
                title: '요일별 지출',
                hAxis: { title: '날짜' },
                vAxis: { title: '금액 (원)' },
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('forchart'));
            chart.draw(chartData, options);
        })
        .catch(error => console.error('Error fetching data:', error));  // 오류 처리
}
