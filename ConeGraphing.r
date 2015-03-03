#!/usr/bin/env Rscript

# Import data from file
data.Cones = read.table("src/Data.txt");

# Draw boxplots to screen (X11)
# data.Cones
pdf("ConeEscape.pdf")
plot(coneEscape~year,main="Cone Escape of Zone per year", data.Cones, xlab="Year", ylab="Cone Escape (%)")
dev.off()

pdf("ConeProduce.pdf")
plot(conesProd~year,main="Cone Produce of Zone per year", data.Cones, xlab="Year", ylab="Cone Produce")
dev.off()

pdf("ConeEaten.pdf")
plot(conesEaten~year,main="Cone Eaten of Zone per year", data.Cones, xlab="Year", ylab="Cone Eaten")
dev.off()

pdf("ConeProd.pdf")
plot(conesEaten~conesProd,main="Cones Produces Per Year vs Cones Eaten", data.Cones, xlab="Cones Produce", ylab="Cone Eaten")
dev.off()

pdf("ConeEscapevsConeProd.pdf")
plot(coneEscape~conesProd,main="Cone Escape vs Cones Produce", data.Cones, xlab="Cone Produce", ylab="Cone Escape")
dev.off()


# boxplot(data.Cones);
# data.Cones = read.table("src/Data.txt");
# png(file="ConeEscape.png", height=800, width=800)
# plot(coneEscape~year, data=data.Cones);
# plot(coneEscape~year, data.Cones, xlab="Year", ylab="Cone Escape")
# plot(coneEscape~year, data.Cones, xlab="Year", ylab="Cone Escape", col=(c("lightblue")) )
# plot(coneEscape~year, data.Cones, xlab="Year", ylab="Cone Escape" )

# Extract Data for a signle RNG (rand-0):
# data.myLCGJ = subset(data.Roll, Algorithm=="myLCG-J")

#plot a histogram
# bins=seq(1,13)
# hist(data.myLCGJ$Roll, breaks=bins, xlab="myLCG-J", col="lightblue")


# Output boxplot to a PNG:
  # lines(coneEscape~year, data.Cones, xlab="Year", ylab="Cone Escape%", col=terrain.colors(6) )
# 
# 
# Output boxplot to a PDF:
# pdf(file="myLCG2.pdf", height=6.5, width=6.5)
# boxplot(Roll~Algorithm, data.Roll, xlab="Algorithm", ylab="Roll (m)", col=terrain.colors(6) )
# dev.off()
# 
# 
# 
# Is there a significant difference between the results?
# anova.Roll = aov(conesProd~source, data=data.Cones)
# summary(anova.Roll)

# # 
# # # pair-wise comparison between groups
# TukeyHSD(anova.Roll)