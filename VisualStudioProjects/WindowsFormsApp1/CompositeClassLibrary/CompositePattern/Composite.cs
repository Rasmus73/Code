using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary.CompositePattern
{
    public class Composite : Component, IEnumerable<Component>
    {
        private List<Component> _children = new List<Component>();

        public Composite(string name)
          : base(name)
        {
        }

        #region Component

        public override void Add(Component component)
        {
            _children.Add(component);
        }

        public override void Remove(Component component)
        {
            _children.Remove(component);
        }

        //public override Component GetChild()
        //{
        //    throw new NotImplementedException();
        //}

        public override void Draw(Graphics graphics)
        {

            System.Drawing.Pen myPen;
            myPen = new System.Drawing.Pen(System.Drawing.Color.Black);

            //graphics.DrawLine(myPen, 1, ,, l.EndX, y);
            System.Drawing.Font font = new Font("TimesNewRoman", 12);
            graphics.DrawString(Name, font, new SolidBrush(Color.Black), 1, 1);

            foreach (Component component in _children)
            {
                component.Draw(graphics);
            }
        }

        //public override void Display(int depth)
        //{
        //    Console.WriteLine(new String('-', depth) + name);

        //    // Recursively display child nodes

        //    foreach (Component component in _children)
        //    {
        //        component.Display(depth + 2);
        //    }
        //}

        #endregion

        #region Composite

        public void SortComponents()
        {
            _children.Sort((a, b) => { return (a.GetType().Name.CompareTo(b.GetType().Name)); });
        }

        #endregion


        #region IEnumerable
        public IEnumerator<Component> GetEnumerator()
        {
            foreach (Component child in _children)
            {
                yield return child;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion

    }
}