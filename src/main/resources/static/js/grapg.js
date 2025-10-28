

  
    //   ************     Chart 1 (total hours in week) js      ***********
    const xValues = ["Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"];
    const yValues = [420, 320, 244, 620, 615, 42, 405, 323];  // Values are in the form of Minutes
    const barColors = ["red", "green", "blue", "orange", "brown"];

    // Calculate total hours
    const totalHours = yValues.reduce((total, hours) => total + hours, 0);

    // Convert total minutes back to the desired format
    const totalHoursDisplay = `${Math.floor(totalHours / 60)}h ${(totalHours % 60).toString().padStart(2, '0')}m`;

    // Update the hours-heading div
    document.querySelector('.chart1-time').textContent = totalHoursDisplay;

    new Chart("myChart", {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [{
                data: yValues,
                backgroundColor: '#7AD5FC'
            }]
        },
        options: {
            legend: {
                display: false
            },
            title: {
                display: false,
                text: `Total Hours This Week`
            },
            scales: {
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    ticks: {
                        display: false
                    },
                    gridLines: {
                        display: false
                    }
                }]
            }
        }
    });

 


  
    //   ************     Chart 2 (FalconShip) js      ***********
    const c2xValues = ["P1", "P2"];
    const c2yValues = [215, 109];  // Values are in the form of Minutes
   

    // Calculate total hours
    const c2totalHours = c2yValues.reduce((total, hours) => total + hours, 0);

    // Convert total minutes back to the desired format
    const c2totalHoursDisplay = `${Math.floor(c2totalHours / 60)}h ${(c2totalHours % 60).toString().padStart(2, '0')}m`;

    // Update the hours-heading div
    document.querySelector('.chart2-time').textContent = c2totalHoursDisplay;

    new Chart("myChart2", {
        type: "bar",
        data: {
            labels: c2xValues,
            datasets: [{
                data: c2yValues,
                backgroundColor: '#B6B4FA'
            }]
        },
        options: {
            legend: {
                display: false
            },
            title: {
                display: false,
                text: `FalconShip`
            },
            scales: {
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    ticks: {
                        display: false,
                        beginAtZero: true, // Adjust the y-axis to start from zero
                        suggestedMax: Math.ceil(Math.max(...c2yValues) / 10) * 10 // Suggested max based on data
                    },
                    gridLines: {
                        display: false
                    }
                }]
            }
        }
    });

    // Update Last Week Time
    document.querySelector('#last-week-time').textContent = '34h 20m';
 


  
    //   ************     Chart 3 (AllFixit) js      ***********
    const c3xValues = ["P1", "P2"];
    const c3yValues = [145, 209];  // Values are in the form of Minutes
   

    // Calculate total hours
    const c3totalHours = c3yValues.reduce((total, hours) => total + hours, 0);

    // Convert total minutes back to the desired format
    const c3totalHoursDisplay = `${Math.floor(c3totalHours / 60)}h ${(c3totalHours % 60).toString().padStart(2, '0')}m`;

    // Update the hours-heading div
    document.querySelector('.chart3-time').textContent = c3totalHoursDisplay;

    new Chart("myChart3", {
        type: "bar",
        data: {
            labels: c3xValues,
            datasets: [{
                data: c3yValues,
                backgroundColor: '#7AD5FC'
            }]
        },
        options: {
            legend: {
                display: false
            },
            title: {
                display: false,
                text: `Graph 3`
            },
            scales: {
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    ticks: {
                        display: false,
                        beginAtZero: true, // Adjust the y-axis to start from zero
                        suggestedMax: Math.ceil(Math.max(...c3yValues) / 10) * 10 // Suggested max based on data
                    },
                    gridLines: {
                        display: false
                    }
                }]
            }
        }
    });

    // Update Last Week Time
    document.querySelector('#mostp-activity').textContent = '14h 20m';
 





  
    //   ************     Chart 4 (total hours in week) js      ***********
    // const c4xValues = ["P1"];
    // const c4yValues = [85];  // Values are in the form of Minutes
    // document.querySelector('.chart4-time').textContent = `${c4yValues[0]}%`;



// ********************* Row 5 Pie Chart *************** (Donut Chart)
const c5xValues = ["FalconShip", "AllFixit", "StarShip"];
const c5yValues = [65, 209, 112];  // Values are in the form of Minutes

// Calculate total hours
const c5totalHours = c5yValues.reduce((total, hours) => total + hours, 0);

// Convert total minutes back to the desired format
const c5totalHoursDisplay = `${Math.floor(c5totalHours / 60)}h ${(c5totalHours % 60).toString().padStart(2, '0')}m`;

// Create the doughnut chart
const myChart5 = new Chart("myChart5", {
    type: "doughnut",
    data: {
        labels: c5xValues,
        datasets: [{
            data: c5yValues,
            backgroundColor: ['#B6B4FA', '#8CA2F8', 'salmon'] // Set different colors for each segment
        }]
    },
    options: {
        cutoutPercentage: 50, // Adjust the size of the hole in the center (0 for a pie chart, 100 for a full circle)
        legend: {
            display: false
        },
        title: {
            display: false,
            text: `Graph 2`
        }
    }
});

// Update userdots and usernames based on the chart data
// const containers = document.querySelectorAll('.d-flex.align-items-center.my-2');
const containers = document.querySelectorAll('.profile-data');

containers.forEach((container, index) => {
    const userdotElements = container.querySelectorAll('.profile-dot.ar-userdot');
    const usernameElements = container.querySelectorAll('.ar-username');

    // Loop through the data and update the elements
    if (userdotElements.length > 0 && usernameElements.length > 0) {
        // Update .ar-userdot bgcolor
        for (let i = 0; i < userdotElements.length; i++) {
            userdotElements[i].style.backgroundColor = myChart5.data.datasets[0].backgroundColor[index];
        }

        // Update .ar-username
        for (let i = 0; i < usernameElements.length; i++) {
            usernameElements[i].textContent = c5xValues[index];
        }
    }
});

// Update Last Week Time
document.querySelector('#last-week-time').textContent = c5totalHoursDisplay;
