#!/usr/bin/env Rscript

# Import data from file
data.Cones = read.table("src/treeDataKP.txt");

# Draw boxplots to screen (X11)
# boxplot(Roll~Algorithm, data=data.Roll);
# boxplot(Roll~Algorithm, data.Roll, xlab="Algorithm", ylab="Roll 2D^");
# boxplot(Roll~Algorithm, data.Roll, xlab="Algorithm", ylab="Roll 2D6", col=(c("lightblue")) )
# boxplot(Roll~Algorithm, data.Roll, xlab="Algorithm", ylab="Roll 2D6", col=terrain.colors(6) )

# Extract Data for a signle RNG (rand-0):
# data.myLCGJ = subset(data.Roll, Algorithm=="myLCG-J")

#plot a histogram
# bins=seq(1,13)
# hist(data.myLCGJ$Roll, breaks=bins, xlab="myLCG-J", col="lightblue")


# Output boxplot to a PNG:
# png(file="myLCG2.png", height=800, width=800)
#   boxplot(Roll~Algorithm, data.Roll, xlab="Algorithm", ylab="Roll (m)", col=terrain.colors(6) )
# dev.off()
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
anova.Roll = aov(conesProd~source, data=data.Cones)
summary(anova.Roll)

# 
# # pair-wise comparison between groups
TukeyHSD(anova.Roll)