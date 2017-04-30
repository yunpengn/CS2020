function Vessel(displacement) {
	this.displacement = displacement; // public
	Vessel.count += 1;
}

Vessel.count = 0;

var v1 = new Vessel(2017);
var v2 = new Vessel(2018);
console.log(v1.displacement);
console.log(Vessel.count);