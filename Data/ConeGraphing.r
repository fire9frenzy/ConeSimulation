data.Cones = read.table("../../Data/Data.txt");
#Draw boxplots to screen (X11)

# data.Cones

pdf("../../Data/ConeEscape.pdf")
plot(coneEscape~year,main="Cone Escape per Density vs Time", data.Cones, xlab="Year", ylab="Cone Escape per Density")
dev.off()

pdf("../../Data/ConeEscapeDensity.pdf")
plot(coneEscapeDensity~year,main="Cone Escape of Zone per year", data.Cones, xlab="Year", ylab="Cone Escape per Tree Density")
dev.off()

pdf("../../Data/ConeProduce.pdf")
plot(conesProd~year,main="Cone Produce of Zone per year", data.Cones, xlab="Year", ylab="Cone Produce")
dev.off()

pdf("../../Data/ConeEaten.pdf")
plot(conesEaten~year,main="Cone Eaten of Zone per year", data.Cones, xlab="Year", ylab="Cone Eaten")
dev.off()

pdf("../../Data/ConeProd.pdf")
plot(conesEaten~conesProd,main="Cones Produces Per Year vs Cones Eaten", data.Cones, xlab="Cones Produce", ylab="Cone Eaten")
dev.off()

pdf("../../Data/ConeEscapevsConeProd.pdf")
plot(coneEscape~conesProd,main="Cone Escape vs Cones Produce", data.Cones, xlab="Cone Produce", ylab="Cone Escape")
dev.off()

