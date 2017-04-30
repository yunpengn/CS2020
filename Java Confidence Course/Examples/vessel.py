class Vessel:
    count = 0
    def __init__(self, displacement):
        self.displacement = displacement
        Vessel.count += 1

v1 = Vessel(2017)
v2 = Vessel(2018)
print(v1.displacement)
print(v1.count)
