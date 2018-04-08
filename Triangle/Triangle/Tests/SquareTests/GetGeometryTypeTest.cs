using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Triangle.Tests.SquareTests
{
    [TestClass]
    public class GetGeometryTypeTest
    {
        [TestMethod]
        [TestCategory("Unittest")]
        public void WhenSquare_ExpectCorrectResult()
        {
            var lines = new List<Line> { new Line(10), new Line(10), new Line(10), new Line(10) };
            var square = new Square(lines);

            var result = square.GetGeometryType();
            Assert.AreEqual(GeometryType.Square, result);
        }

        [TestMethod]
        [TestCategory("Unittest")]
        public void WhenUnknownSquare_ExpectCorrectResult()
        {
            var lines = new List<Line> { new Line(10), new Line(20), new Line(30), new Line(40) };
            var square = new Square(lines);

            var result = square.GetGeometryType();
            Assert.AreEqual(GeometryType.UnknownShape, result);
        }

        [TestMethod]
        [TestCategory("Unittest")]
        [ExpectedException(typeof(ArgumentNullException))]
        public void WhenSquareIsNull_ExpectArgumentNullException()
        {
            var square = new Square(null);
            square.GetGeometryType();
        }

        [TestMethod]
        [TestCategory("Unittest")]
        public void WhenWrongNumberOfLinesSquare_ExpectCorrectResult()
        {
            //less than 4
            var lines = new List<Line> { new Line(10), new Line(20) };
            var square = new Square(lines);
            var result = square.GetGeometryType();

            Assert.AreEqual(GeometryType.UnknownShape, result);

            //more than 4
            lines.Add(new Line(20));
            lines.Add(new Line(20));
            lines.Add(new Line(20));
            lines.Add(new Line(20));
            square = new Square(lines);
            result = square.GetGeometryType();

            Assert.AreEqual(GeometryType.UnknownShape, result);
        }
    }
}
