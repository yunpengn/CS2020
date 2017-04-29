{
    init: function(elevators, floors) {
        
    },
        update: function(dt, elevators, floors) {
            var i = 0;
            for (i = 0; i < elevators.length; i++) {
                var elevator = elevators[i];
                var position = elevator.currentFloor();
                var direction = elevator.destinationDirection === "up" ? 1 : -1;

                if (position === floors.length - 1) {
                    elevator.goToFloor(position - 1);
                } else if (position === 0) {
                    elevator.goToFloor(position + 1);
                } else {
                    elevator.goToFloor(position + direction);
                }
            }
        }
}