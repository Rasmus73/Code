using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Triangle.Tests.TriangleTests
{
    [TestClass]
    public class GetGeometryTypeTest
    {
        [TestMethod]
        [TestCategory("Unittest")]
        public void WhenEquilateralTriangle_ExpectCorrectResult()
        {
            var lines = new List<Line> { new Line(10), new Line(10), new Line(10)};
            var triangle = new Triangle(lines);

            var result = triangle.GetGeometryType();
            Assert.AreEqual(GeometryType.EquilateralTriangle, result);
        }

        [TestMethod]
        [TestCategory("Unittest")]
        public void WhenIsoscelesTriangle_ExpectCorrectResult()
        {
            var lines = new List<Line> { new Line(10), new Line(10), new Line(5) };
            var triangle = new Triangle(lines);

            var result = triangle.GetGeometryType();
            Assert.AreEqual(GeometryType.IsoscelesTriangle, result);
        }

        [TestMethod]
        [TestCategory("Unittest")]
        [ExpectedException(typeof(ArgumentNullException))]
        public void WhenNullLinesTriangle_ExpectArgumentNullException()
        {
            var triangle = new Triangle(null);
            triangle.GetGeometryType();
        }

        [TestMethod]
        [TestCategory("Unittest")]
        public void WhenUnknownTriangle_ExpectCorrectResult()
        {
            var lines = new List<Line> { new Line(10), new Line(20), new Line(30)};
            var triangle = new Triangle(lines);

            var result = triangle.GetGeometryType();
            Assert.AreEqual(GeometryType.UnknownShape, result);
        }

        [TestMethod]
        [TestCategory("Unittest")]
        public void WhenWrongNumberOfLinesTriangle_ExpectCorrectResult()
        {
            //less than 2
            var lines = new List<Line> { new Line(10), new Line(20)};
            var triangle = new Triangle(lines);
            var result = triangle.GetGeometryType();

            Assert.AreEqual(GeometryType.UnknownShape, result);

            //more than 3
            lines.Add(new Line(20));
            lines.Add(new Line(20));
            triangle = new Triangle(lines);
            result = triangle.GetGeometryType();

            Assert.AreEqual(GeometryType.UnknownShape, result);
        }
    }
}
